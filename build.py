"""Build the offline reconstruction without Gradle. Requires Python 3.9+, JDK 17 and Android SDK 34."""
import argparse,pathlib,os,subprocess,zipfile,shutil
root=pathlib.Path(__file__).resolve().parent
parser=argparse.ArgumentParser()
parser.add_argument('--java-home',default=os.environ.get('JAVA_HOME'))
parser.add_argument('--sdk',default=os.environ.get('ANDROID_SDK_ROOT') or os.environ.get('ANDROID_HOME'))
parser.add_argument('--build-tools',default='34.0.0')
args=parser.parse_args()
if not args.java_home or not args.sdk:parser.error('Set JAVA_HOME and ANDROID_SDK_ROOT, or pass --java-home and --sdk')
java=pathlib.Path(args.java_home)/'bin';sdk=pathlib.Path(args.sdk)
bt=sdk/'build-tools'/args.build_tools;android=sdk/'platforms/android-34/android.jar'
# Also supports the unmodified directory names in Google's standalone SDK archives.
if not bt.exists() and (sdk/'android-14').exists():bt=sdk/'android-14'
if not android.exists():android=sdk/'android-34/android.jar'
ext='.exe' if os.name=='nt' else ''
def run(items):subprocess.run([str(i) for i in items],check=True)
b=root/'build'
for d in ['gen','classes','dex']:(b/d).mkdir(parents=True,exist_ok=True)
for p in (b/'classes').rglob('*.class'):p.unlink()
run([bt/('aapt'+ext),'package','-f','-M',root/'AndroidManifest.xml','-S',root/'res','-I',android,'-F',b/'base.apk','-J',b/'gen'])
run([java/('javac'+ext),'--release','8','-encoding','UTF-8','-classpath',android,'-d',b/'classes',*list((root/'src').rglob('*.java')),*list((b/'gen').rglob('*.java'))])
with zipfile.ZipFile(b/'classes.jar','w') as z:
 for p in (b/'classes').rglob('*.class'):z.write(p,p.relative_to(b/'classes').as_posix())
run([java/('java'+ext),'-cp',bt/'lib/d8.jar','com.android.tools.r8.D8','--lib',android,'--min-api','24','--output',b/'dex',b/'classes.jar'])
shutil.copy2(b/'base.apk',b/'unsigned.apk')
with zipfile.ZipFile(b/'unsigned.apk','a',compression=zipfile.ZIP_DEFLATED) as z:
 for p in (b/'dex').glob('*.dex'):z.write(p,p.name)
 for p in (root/'assets').rglob('*'):
  if p.is_file():z.write(p,'assets/'+p.relative_to(root/'assets').as_posix())
key=root/'local-build.keystore'
if not key.exists():run([java/('keytool'+ext),'-genkeypair','-keystore',key,'-storepass','android','-keypass','android','-alias','rebuild','-keyalg','RSA','-keysize','2048','-validity','3650','-dname','CN=Local Reconstruction'])
run([bt/('zipalign'+ext),'-f','4',b/'unsigned.apk',b/'aligned.apk'])
run([java/('java'+ext),'-jar',bt/'lib/apksigner.jar','sign','--ks',key,'--ks-key-alias','rebuild','--ks-pass','pass:android','--key-pass','pass:android','--out',b/'app.apk',b/'aligned.apk'])
run([java/('java'+ext),'-jar',bt/'lib/apksigner.jar','verify',b/'app.apk'])
print('Built',b/'app.apk')
