    @Override // N1.a
    public final Object a() {
        int i3 = 0;
        zwpview zwpviewVar = this.f7719e;
        switch (this.f7718d) {
            case 0:
                zwpviewVar.setCurrentClickTime(System.currentTimeMillis());
                if (zwpviewVar.getCurrentClickTime() - zwpviewVar.getLastClickTime() < 900) {
                    String str = zwpviewVar.getnowtimestr();
                    List listI0 = V1.d.i0(zwpviewVar.getS(), new String[]{"|"});
                    MyApplication myApplication = x.f7648a;
                    String strB = S0.g.B("jingdu", "");
                    StringBuilder sb = new StringBuilder();
                    sb.append((String) listI0.get(0));
                    sb.append('|');
                    sb.append(str);
                    sb.append('|');
                    sb.append((String) B.g.e(sb, (String) listI0.get(7), '|', listI0, 8));
                    sb.append('|');
                    sb.append(zwpviewVar.getGsex());
                    sb.append('|');
                    sb.append(strB);
                    zwpviewVar.s(sb.toString(), "");
                }
                zwpviewVar.setLastClickTime(zwpviewVar.getCurrentClickTime());
                return C1.g.f235c;
            case 1:
                MyApplication myApplication2 = x.f7648a;
                String strB2 = S0.g.B("ltimes", "");
                int i4 = strB2.length() > 0 ? Integer.parseInt(strB2) : 0;
                if (System.currentTimeMillis() < 1736903439000L) {
                    String strB3 = S0.g.B("zengsong", "");
                    if (strB3.length() > 0) {
                        i3 = Integer.parseInt(strB3);
                    }
                }
                if (i4 >= 1 || i3 > 0) {
                    Intent intent = new Intent(zwpviewVar.getContext(), (Class<?>) AIjiepan.class);
                    intent.putExtra("txt", zwpviewVar.getAIstr());
                    zwpviewVar.getContext().startActivity(intent);
                } else {
                    Intent intent2 = new Intent(zwpviewVar.getContext(), (Class<?>) chongzhitishi.class);
                    intent2.putExtra("txt", zwpviewVar.getAIstr());
                    zwpviewVar.getContext().startActivity(intent2);
                }
                return C1.g.f235c;
            case 2:
                Object systemService = zwpviewVar.getContext().getSystemService("clipboard");
                O1.f.c("null cannot be cast to non-null type android.content.ClipboardManager", systemService);
                ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText("", zwpviewVar.getPanmianstr()));
                MyApplication myApplication3 = x.f7648a;
                S0.g.I("已复制盘面信息", "已复制盘面信息，你可以到各种AI里面粘贴文本进行测算了");
                return C1.g.f235c;
            case 3:
                zwpviewVar.setCurrentClickTime2(System.currentTimeMillis());
                if (zwpviewVar.getCurrentClickTime2() - zwpviewVar.getLastClickTime2() < 900) {
                    System.out.println((Object) "exit");
                    Context context = zwpviewVar.getContext();
                    O1.f.d("getContext(...)", context);
                    Intent intent3 = new Intent("android.intent.action.MAIN");
                    intent3.addCategory("android.intent.category.HOME");
                    intent3.setFlags(268435456);
                    context.startActivity(intent3);
                    new Handler(Looper.getMainLooper()).postDelayed(new u0(1), 500L);
                }
                zwpviewVar.setLastClickTime2(zwpviewVar.getCurrentClickTime2());
                return C1.g.f235c;
            case 4:
                zwpviewVar.setCurrentClickTime5(System.currentTimeMillis());
                if (zwpviewVar.getCurrentClickTime5() - zwpviewVar.getLastClickTime5() < 900) {
                    zwpviewVar.s(zwpviewVar.getS() + "|1", "");
                }
                zwpviewVar.setLastClickTime5(zwpviewVar.getCurrentClickTime5());
                return C1.g.f235c;
            case 5:
                zwpviewVar.setCurrentClickTime5(System.currentTimeMillis());
                if (zwpviewVar.getCurrentClickTime5() - zwpviewVar.getLastClickTime5() < 900) {
                    zwpviewVar.s(zwpviewVar.getS() + "|1", "");
                }
                zwpviewVar.setLastClickTime5(zwpviewVar.getCurrentClickTime5());
                return C1.g.f235c;
            case 6:
                zwpviewVar.setCurrentClickTime6(System.currentTimeMillis());
                if (zwpviewVar.getCurrentClickTime6() - zwpviewVar.getLastClickTime6() < 900) {
                    zwpviewVar.s(zwpviewVar.getS() + "|2", "");
                }
                zwpviewVar.setLastClickTime6(zwpviewVar.getCurrentClickTime6());
                return C1.g.f235c;
            case 7:
                zwpviewVar.setCurrentClickTime7(System.currentTimeMillis());
                zwpviewVar.setShowCentTime(zwpviewVar.getShowCentTime() + 1);
                if (zwpviewVar.getShowCentTime() == 3) {
                    zwpviewVar.setShowCentTime(0);
                }
                zwpviewVar.setLastClickTime7(zwpviewVar.getCurrentClickTime7());
                return C1.g.f235c;
            case 8:
                if (zwpviewVar.getQiehuan() < 6) {
                    zwpviewVar.setQiehuan(zwpviewVar.getQiehuan() + 1);
                } else {
                    zwpviewVar.setQiehuan(0);
                }
                return C1.g.f235c;
            case 9:
                zwpviewVar.setLiupantype(zwpviewVar.getLiupantype() + 1);
                zwpviewVar.setLiupantype(zwpviewVar.getLiupantype() % 3);
                if (zwpviewVar.getLiupantype() == 0) {
                    zwpviewVar.setLiupanflag(false);
                }
                if (zwpviewVar.getLiupantype() == 1) {
                    zwpviewVar.setLiupanflag(true);
                }
                if (zwpviewVar.getLiupantype() == 2) {
                    zwpviewVar.setLiupanflag(true);
                }
                return C1.g.f235c;
            case 10:
                zwpviewVar.setLiupanflag(false);
                zwpviewVar.setLiupantype(0);
                zwpviewVar.s(zwpviewVar.getSex2s(), "");
                zwpviewVar.setDaxianzhi("");
                return C1.g.f235c;
            case 11:
                if (zwpviewVar.getLiupanflag()) {
                    zwpviewVar.setLiurishiflag(true);
                    if (zwpviewVar.getLiupantype() == 1) {
                        zwpviewVar.setDaxianzhi(zwpviewVar.o(zwpviewVar.getDaxianzhi(), -1));
                        zwpviewVar.setXusui(Integer.parseInt(zwpviewVar.getDaxianlist().a(zwpviewVar.getDaxianzhi()).b("起始")));
                    }
                    if (zwpviewVar.getLiupantype() == 2) {
                        zwpviewVar.setXusui(zwpviewVar.getXusui() - 1);
                        if (zwpviewVar.getXusui() < 1) {
                            zwpviewVar.setXusui(120);
                        }
                        if (zwpviewVar.getXusui() < Integer.parseInt(zwpviewVar.getDaxianlist().a(zwpviewVar.getDaxianzhi()).b("起始"))) {
                            zwpviewVar.setDaxianzhi(zwpviewVar.o(zwpviewVar.getDaxianzhi(), -1));
                            int i5 = Integer.parseInt(zwpviewVar.getDaxianlist().a(zwpviewVar.getDaxianzhi()).b("起始"));
                            if (zwpviewVar.getXusui() < i5 || zwpviewVar.getXusui() > i5 + 9) {
                                zwpviewVar.setDaxianzhi(zwpviewVar.o(zwpviewVar.getDaxianzhi(), 2));
                            }
                        }
                    }
                    zwpviewVar.setClicked(false);
                } else {
                    Intent intent4 = new Intent(zwpviewVar.getContext(), (Class<?>) shuruye.class);
                    intent4.putExtra("s", zwpviewVar.getS());
                    intent4.putExtra("name", zwpviewVar.getGnme());
                    zwpviewVar.getContext().startActivity(intent4);
                }
                return C1.g.f235c;
            case 12:
                if (zwpviewVar.getLiupanflag()) {
                    zwpviewVar.setLiurishiflag(true);
                    if (zwpviewVar.getLiupantype() == 1) {
                        zwpviewVar.setDaxianzhi(zwpviewVar.o(zwpviewVar.getDaxianzhi(), 1));
                        zwpviewVar.setXusui(Integer.parseInt(zwpviewVar.getDaxianlist().a(zwpviewVar.getDaxianzhi()).b("起始")));
                    }
                    if (zwpviewVar.getLiupantype() == 2) {
                        zwpviewVar.setXusui(zwpviewVar.getXusui() + 1);
                        if (zwpviewVar.getXusui() > Integer.parseInt(zwpviewVar.getDaxianlist().a(zwpviewVar.getDaxianzhi()).b("起始")) + 9) {
                            zwpviewVar.setDaxianzhi(zwpviewVar.o(zwpviewVar.getDaxianzhi(), 1));
                            int i6 = Integer.parseInt(zwpviewVar.getDaxianlist().a(zwpviewVar.getDaxianzhi()).b("起始"));
                            if (zwpviewVar.getXusui() < i6 || zwpviewVar.getXusui() > i6 + 9) {
                                zwpviewVar.setDaxianzhi(zwpviewVar.o(zwpviewVar.getDaxianzhi(), -2));
                            }
                        }
                    }
                    zwpviewVar.setClicked(false);
                } else {
                    Intent intent5 = new Intent(zwpviewVar.getContext(), (Class<?>) gongjuji.class);
                    intent5.putExtra("s", zwpviewVar.getS());
                    intent5.putExtra("name", zwpviewVar.getGnme());
                    zwpviewVar.getContext().startActivity(intent5);
                }
                return C1.g.f235c;
            case 13:
                if (!zwpviewVar.getLiupanflag()) {
                    Intent intent6 = new Intent(zwpviewVar.getContext(), (Class<?>) gongjuji.class);
                    intent6.putExtra("s", zwpviewVar.getS());
                    intent6.putExtra("name", zwpviewVar.getGnme());
                    zwpviewVar.getContext().startActivity(intent6);
                }
                return C1.g.f235c;
            case 14:
                Intent intent7 = new Intent(zwpviewVar.getContext(), (Class<?>) shuruye.class);
                intent7.putExtra("s", zwpviewVar.getS());
                intent7.putExtra("name", zwpviewVar.getGnme());
                zwpviewVar.getContext().startActivity(intent7);
                return C1.g.f235c;
            case 15:
                zwpviewVar.setCurrentClickTime4(System.currentTimeMillis());
                if (zwpviewVar.getCurrentClickTime4() - zwpviewVar.getLastClickTime4() < 800) {
                    MyApplication myApplication4 = x.f7648a;
                    S0.g.I("请勿重复点击", "请勿重复点击，只需点一次，排盘需要一些时间，请耐心等待。");
                } else {
                    Intent intent8 = new Intent(zwpviewVar.getContext(), (Class<?>) ziweipan.class);
                    if (O1.f.a(zwpviewVar.getGudingxingbie(), "0")) {
                        intent8.putExtra("s", zwpviewVar.getCheck() + "|11|suiji|" + zwpviewVar.getDefaulesex());
                    } else {
                        intent8.putExtra("s", zwpviewVar.getCheck() + "|11|suiji");
                    }
                    MyApplication myApplication5 = x.f7648a;
                    S0.g.B("lastsuijiflag", "1");
                    zwpviewVar.getContext().startActivity(intent8);
                }
                zwpviewVar.setLastClickTime4(zwpviewVar.getCurrentClickTime4());
                return C1.g.f235c;
            case 16:
                zwpviewVar.setCurrentClickTime3(System.currentTimeMillis());
                if (zwpviewVar.getCurrentClickTime3() - zwpviewVar.getLastClickTime3() < 900) {
                    int i7 = Integer.parseInt(zwpviewVar.getYinliri());
                    if (i7 < 19) {
                        i7 += 12;
                    } else if (i7 > 18) {
                        i7 -= 12;
                    }
                    zwpviewVar.setYinliri(String.valueOf(i7));
                    List listI1 = V1.d.i0(zwpviewVar.getS(), new String[]{"|"});
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(V1.l.L((String) listI1.get(0), "#1", "#3"));
                    sb2.append('|');
                    sb2.append(zwpviewVar.getYinlinian());
                    sb2.append('|');
                    sb2.append(zwpviewVar.getYinliyue());
                    sb2.append('|');
                    sb2.append(zwpviewVar.getYinliri());
                    sb2.append('|');
                    sb2.append((String) B.g.e(sb2, (String) B.g.e(sb2, (String) B.g.e(sb2, (String) B.g.e(sb2, (String) listI1.get(4), '|', listI1, 5), '|', listI1, 6), '|', listI1, 7), '|', listI1, 8));
                    sb2.append('|');
                    sb2.append(zwpviewVar.getGsex());
                    sb2.append('|');
                    sb2.append((String) listI1.get(10));
                    sb2.append('|');
                    sb2.append(zwpviewVar.getYinlirun());
                    zwpviewVar.s(sb2.toString(), "");
                }
                zwpviewVar.setLastClickTime3(zwpviewVar.getCurrentClickTime3());
                return C1.g.f235c;
            case 17:
                zwpviewVar.setCurrentClickTime(System.currentTimeMillis());
                if (zwpviewVar.getCurrentClickTime() - zwpviewVar.getLastClickTime() < 900) {
                    String str2 = zwpviewVar.getnowtimestr();
                    List listI2 = V1.d.i0(zwpviewVar.getS(), new String[]{"|"});
                    MyApplication myApplication6 = x.f7648a;
                    String strB4 = S0.g.B("jingdu", "");
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append((String) listI2.get(0));
                    sb3.append('|');
                    sb3.append(str2);
                    sb3.append('|');
                    sb3.append((String) B.g.e(sb3, (String) listI2.get(7), '|', listI2, 8));
                    sb3.append('|');
                    sb3.append(zwpviewVar.getGsex());
                    sb3.append('|');
                    sb3.append(strB4);
                    zwpviewVar.s(sb3.toString(), "");
                }
                zwpviewVar.setLastClickTime(zwpviewVar.getCurrentClickTime());
                return C1.g.f235c;
            default:
                try {
                    return Typeface.createFromAsset(zwpviewVar.getContext().getAssets(), "fonts/jiaguwen.ttf");
                } catch (Exception unused) {
                    return null;
                }
        }
    }