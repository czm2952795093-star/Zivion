    /* JADX WARN: Code duplicated, block: B:152:0x0810  */
    /* JADX WARN: Code duplicated, block: B:155:0x0823 A[LOOP:5: B:150:0x080a->B:155:0x0823, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:158:0x0860  */
    /* JADX WARN: Code duplicated, block: B:160:0x086a  */
    /* JADX WARN: Code duplicated, block: B:163:0x0880 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:166:0x0885  */
    /* JADX WARN: Code duplicated, block: B:170:0x08f4  */
    /* JADX WARN: Code duplicated, block: B:172:0x091d  */
    /* JADX WARN: Code duplicated, block: B:175:0x0963  */
    /* JADX WARN: Code duplicated, block: B:288:0x096e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:290:0x0820 A[EDGE_INSN: B:290:0x0820->B:154:0x0820 BREAK  A[LOOP:5: B:150:0x080a->B:155:0x0823], SYNTHETIC] */
    public final void a(Canvas canvas) {
        String str;
        int i3;
        Paint paint;
        String str2;
        int i4;
        Canvas canvas2;
        float f;
        float f3;
        float fA;
        float f4;
        float fB;
        float fA2;
        float f5;
        float f6;
        float fA3;
        float fB2;
        float fA4;
        float fB3;
        float fB4;
        float fB5;
        float fA5;
        float fB6;
        float fA6;
        float fB7;
        float fA7;
        float fB8;
        float fB9;
        float fA8;
        float fA9;
        float fB10;
        float fA10;
        float fB11;
        float fA11;
        float fB12;
        float fA12;
        float fB13;
        float fA13;
        float fB14;
        float f7;
        float fA14;
        float fB15;
        float fA15;
        float fB16;
        float f8;
        float f9;
        float fA16;
        float fB17;
        float fA17;
        float fB18;
        float fA18;
        float fB19;
        float fA19;
        float fB20;
        float f10;
        float fA20;
        float fA21;
        float fA22;
        float fA23;
        float fB21;
        Paint paint2;
        Paint paint3;
        int i5;
        Iterator itD;
        Paint paint4;
        int i6;
        List list;
        ArrayList arrayList;
        float fH;
        float fH2;
        float f11;
        w2 w2Var;
        int i7;
        Paint paint5;
        int i8;
        List list2;
        String str3;
        ArrayList arrayList2;
        String str4;
        Iterator it;
        if (this.f3503w0 && this.f3414U1 == 0) {
            return;
        }
        Q qA = this.f3511z0.a("十二宫信息");
        Q qA2 = qA.a(this.f3500v0);
        this.f3459j1 = qA2.b("宫");
        Q qA3 = qA2.a("四化");
        this.f3456i1.clear();
        Iterator itD2 = qA3.d();
        while (itD2.hasNext()) {
            String strB = qA3.a((String) itD2.next()).b("宫");
            if (!this.f3456i1.contains(strB)) {
                this.f3456i1.add(strB);
            }
        }
        if (O1.f.a(this.f3500v0, "子")) {
            String strB2 = qA.a("寅").b("宫");
            if (!this.f3456i1.contains(strB2)) {
                this.f3456i1.add(strB2);
            }
        }
        if (O1.f.a(this.f3500v0, "寅")) {
            String strB3 = qA.a("子").b("宫");
            if (!this.f3456i1.contains(strB3)) {
                this.f3456i1.add(strB3);
            }
        }
        if (O1.f.a(this.f3500v0, "丑")) {
            String strB4 = qA.a("卯").b("宫");
            if (!this.f3456i1.contains(strB4)) {
                this.f3456i1.add(strB4);
            }
        }
        if (O1.f.a(this.f3500v0, "卯")) {
            String strB5 = qA.a("丑").b("宫");
            if (!this.f3456i1.contains(strB5)) {
                this.f3456i1.add(strB5);
            }
        }
        List<String> listL = h.L(h.J(qA3.d()));
        String strB6 = qA2.b("宫");
        String str5 = this.f3394O + '|' + this.f3500v0 + '|' + this.f3414U1 + '|' + this.f3422X1 + '|' + this.f3439d2 + '|' + getWidth() + '|' + getHeight();
        boolean zA = O1.f.a(str5, this.f3466l0);
        ArrayList arrayList3 = this.f3506x0;
        String str6 = "星列表";
        String str7 = "支";
        String str8 = "化";
        List list3 = this.f3360B0;
        String str9 = "科";
        String str10 = "禄";
        if (!zA) {
            this.f3466l0 = str5;
            w2 w2Var2 = this.f3492s2;
            w2Var2.f7646a = 0.0f;
            w2Var2.f7647b = 0.0f;
            w2 w2Var3 = this.t2;
            w2Var3.f7646a = 0.0f;
            w2Var3.f7647b = 0.0f;
            w2 w2Var4 = this.u2;
            w2Var4.f7646a = 0.0f;
            w2Var4.f7647b = 0.0f;
            w2 w2Var5 = this.v2;
            w2Var5.f7646a = 0.0f;
            w2Var5.f7647b = 0.0f;
            w2 w2Var6 = this.w2;
            w2Var6.f7646a = 0.0f;
            w2Var6.f7647b = 0.0f;
            w2 w2Var7 = this.x2;
            w2Var7.f7646a = 0.0f;
            w2Var7.f7647b = 0.0f;
            w2 w2Var8 = this.y2;
            w2Var8.f7646a = 0.0f;
            w2Var8.f7647b = 0.0f;
            w2 w2Var9 = this.z2;
            w2Var9.f7646a = 0.0f;
            w2Var9.f7647b = 0.0f;
            Iterator it2 = listL.iterator();
            while (it2.hasNext()) {
                String str11 = (String) it2.next();
                Q qA4 = qA3.a(str11);
                Iterator it3 = it2;
                String strB7 = qA4.b("化");
                String strB8 = qA4.b(str7);
                String str12 = str7;
                Iterator itD3 = qA.a(strB8).a(str6).d();
                String str13 = str6;
                int i9 = 0;
                while (itD3.hasNext()) {
                    Iterator it4 = itD3;
                    if (O1.f.a((String) itD3.next(), str11)) {
                        break;
                    }
                    i9++;
                    itD3 = it4;
                }
                w2 w2Var10 = (w2) arrayList3.get(list3.indexOf(strB8));
                float fH3 = h(1.0f) + (i9 * this.f3486r0) + w2Var10.f7646a;
                float fH4 = h(29.0f) + (2 * this.f3490s0) + w2Var10.f7647b;
                if (this.f3439d2) {
                    fH4 = h(11.0f) + fH4;
                }
                if (O1.f.a(strB7, "禄")) {
                    w2 w2Var11 = this.f3492s2;
                    if (w2Var11.f7646a == 0.0f) {
                        w2Var11.f7646a = fH3;
                        w2Var11.f7647b = fH4 - h(21.0f);
                    }
                }
                if (O1.f.a(strB7, "权")) {
                    w2 w2Var12 = this.t2;
                    if (w2Var12.f7646a == 0.0f) {
                        w2Var12.f7646a = fH3;
                        w2Var12.f7647b = fH4 - h(21.0f);
                    }
                }
                if (O1.f.a(strB7, "科")) {
                    w2 w2Var13 = this.u2;
                    if (w2Var13.f7646a == 0.0f) {
                        w2Var13.f7646a = fH3;
                        w2Var13.f7647b = fH4 - h(21.0f);
                    }
                }
                if (O1.f.a(strB7, "忌")) {
                    w2 w2Var14 = this.v2;
                    if (w2Var14.f7646a == 0.0f) {
                        w2Var14.f7646a = fH3;
                        w2Var14.f7647b = fH4 - h(21.0f);
                    }
                }
                if (this.f3414U1 > 0 && O1.f.a(this.f3500v0, this.f3422X1)) {
                    if (O1.f.a(strB7, "禄")) {
                        w2 w2Var15 = this.w2;
                        w2Var15.f7646a = fH3;
                        w2Var15.f7647b = fH4 - h(11.0f);
                    }
                    if (O1.f.a(strB7, "权")) {
                        w2 w2Var16 = this.x2;
                        w2Var16.f7646a = fH3;
                        w2Var16.f7647b = fH4 - h(11.0f);
                    }
                    if (O1.f.a(strB7, "科")) {
                        w2 w2Var17 = this.y2;
                        w2Var17.f7646a = fH3;
                        w2Var17.f7647b = fH4 - h(11.0f);
                    }
                    if (O1.f.a(strB7, "忌")) {
                        w2 w2Var18 = this.z2;
                        w2Var18.f7646a = fH3;
                        w2Var18.f7647b = fH4 - h(11.0f);
                    }
                }
                it2 = it3;
                str7 = str12;
                str6 = str13;
            }
        }
        String str14 = str7;
        String str15 = str6;
        String str16 = this.f3500v0;
        List listO = j.O("父", "福", "田", "官", "友", "迁", "疾", "财", "子", "夫", "兄", "命");
        if (O1.f.a(strB6, "交友")) {
            strB6 = "友交";
        }
        String strSubstring = strB6.substring(0, 1);
        String str17 = "substring(...)";
        O1.f.d("substring(...)", strSubstring);
        String strConcat = strSubstring.concat("之");
        Paint paint6 = this.f3479p;
        ArrayList arrayList4 = arrayList3;
        paint6.setTypeface(O1.f.a(this.U2, "1") ? getJiaguwenTypeface() : null);
        paint6.setTextAlign(Paint.Align.LEFT);
        paint6.setFakeBoldText(false);
        paint6.setTextSize(h(7.0f));
        paint6.setColor(this.f3416V0);
        int iIndexOf = list3.indexOf(str16);
        int i10 = 1;
        while (true) {
            str = str17;
            if (i10 >= 13) {
                break;
            }
            StringBuilder sbA = e.a(strConcat);
            String str18 = strConcat;
            sbA.append((String) listO.get(i10 - 1));
            String string = sbA.toString();
            w2 w2Var19 = (w2) this.f3506x0.get((iIndexOf + i10) % 12);
            canvas.drawText(string, (w2Var19.f7646a + this.f3482q0) - h(62.0f), (w2Var19.f7647b + this.p0) - h(5.0f), paint6);
            i10++;
            str17 = str;
            strConcat = str18;
            iIndexOf = iIndexOf;
        }
        String str19 = this.f3500v0;
        if (this.S2.length() != 0 && !O1.f.a(this.S2, "0") && !O1.f.a(str19, this.T2)) {
            Paint paint7 = this.f3479p;
            paint7.setTypeface(null);
            paint7.setTextAlign(Paint.Align.LEFT);
            paint7.setTextSize(h(8.0f));
            paint7.setFakeBoldText(true);
            paint7.setColor(this.f3423Y0);
            List list4 = this.f3360B0;
            int iIndexOf2 = ((list4.indexOf(str19) + this.Q2) + 24) % 12;
            int iIndexOf3 = ((list4.indexOf(str19) + this.R2) + 24) % 12;
            ArrayList arrayList5 = this.f3506x0;
            w2 w2Var20 = (w2) arrayList5.get(iIndexOf2);
            canvas.drawText("2", (w2Var20.f7646a + this.f3482q0) - h(50.0f), (w2Var20.f7647b + this.p0) - h(45.0f), paint7);
            w2 w2Var21 = (w2) arrayList5.get(iIndexOf3);
            canvas.drawText("3", (w2Var21.f7646a + this.f3482q0) - h(55.0f), (w2Var21.f7647b + this.p0) - h(45.0f), paint7);
        }
        t(canvas, this.f3500v0, qA2.b("追禄"), qA2.b("追忌"));
        Paint paint8 = this.f3468m;
        paint8.setTypeface(O1.f.a(this.U2, "1") ? getJiaguwenTypeface() : null);
        paint8.setTextAlign(Paint.Align.LEFT);
        paint8.setUnderlineText(false);
        paint8.setFakeBoldText(false);
        Paint paint9 = this.f3472n;
        Paint paint10 = this.f3475o;
        boolean z2 = this.f3439d2;
        int i11 = this.f3425Z0;
        int i12 = this.f3423Y0;
        int i13 = this.f3421X0;
        List list5 = list3;
        int i14 = this.f3419W0;
        Q q3 = qA;
        if (z2) {
            if (this.f3492s2.f7646a == 0.0f) {
                i3 = i11;
                paint = paint9;
                str2 = "权";
                i4 = i12;
                canvas2 = canvas;
            } else {
                paint8.setTextSize(h(10.0f));
                paint8.setColor(-1);
                paint9.setColor(i14);
                w2 w2Var22 = this.f3492s2;
                i4 = i12;
                i3 = i11;
                str2 = "权";
                canvas2 = canvas;
                canvas.drawRect(w2Var22.f7646a, w2Var22.f7647b - h(9.0f), h(10.0f) + this.f3492s2.f7646a, h(3.0f) + this.f3492s2.f7647b, paint9);
                w2 w2Var23 = this.f3492s2;
                canvas2.drawText("禄", w2Var23.f7646a, h(1.0f) + w2Var23.f7647b, paint8);
                paint9.setColor(i13);
                w2 w2Var24 = this.t2;
                canvas.drawRect(w2Var24.f7646a, w2Var24.f7647b - h(9.0f), h(10.0f) + this.t2.f7646a, h(3.0f) + this.t2.f7647b, paint9);
                w2 w2Var25 = this.t2;
                canvas2.drawText(str2, w2Var25.f7646a, h(1.0f) + w2Var25.f7647b, paint8);
                paint9.setColor(i4);
                w2 w2Var26 = this.u2;
                canvas.drawRect(w2Var26.f7646a, w2Var26.f7647b - h(9.0f), h(10.0f) + this.u2.f7646a, h(3.0f) + this.u2.f7647b, paint9);
                w2 w2Var27 = this.u2;
                canvas2.drawText("科", w2Var27.f7646a, h(1.0f) + w2Var27.f7647b, paint8);
                paint9.setColor(i3);
                w2 w2Var28 = this.v2;
                paint = paint9;
                canvas.drawRect(w2Var28.f7646a, w2Var28.b() - h(9.0f), h(10.0f) + this.v2.a(), h(3.0f) + this.v2.b(), paint9);
                canvas2.drawText("忌", this.v2.a(), h(1.0f) + this.v2.b(), paint8);
            }
            if (this.f3414U1 == 2) {
                paint8.setTextSize(h(10.0f));
                paint8.setColor(-1);
                Paint paint11 = paint;
                paint11.setColor(i14);
                canvas.drawRect(this.w2.a(), this.w2.b() - h(9.0f), h(10.0f) + this.w2.a(), h(3.0f) + this.w2.b(), paint11);
                canvas2.drawText("禄", this.w2.a(), h(1.0f) + this.w2.b(), paint8);
                paint11.setColor(i13);
                canvas.drawRect(this.x2.a(), this.x2.b() - h(9.0f), h(10.0f) + this.x2.a(), h(3.0f) + this.x2.b(), paint11);
                canvas2.drawText(str2, this.x2.a(), h(1.0f) + this.x2.b(), paint8);
                paint11.setColor(i4);
                canvas.drawRect(this.y2.a(), this.y2.b() - h(9.0f), h(10.0f) + this.y2.a(), h(3.0f) + this.y2.b(), paint11);
                canvas2.drawText("科", this.y2.a(), h(1.0f) + this.y2.b(), paint8);
                paint11.setColor(i3);
                paint = paint11;
                canvas.drawRect(this.z2.a(), this.z2.b() - h(9.0f), h(10.0f) + this.z2.a(), h(3.0f) + this.z2.b(), paint11);
                canvas2.drawText("忌", this.z2.a(), h(1.0f) + this.z2.b(), paint8);
            }
        } else {
            i3 = i11;
            paint10 = paint10;
            paint = paint9;
            str8 = "化";
            arrayList4 = arrayList4;
            str14 = str14;
            str2 = "权";
            i4 = i12;
            qA3 = qA3;
            canvas2 = canvas;
        }
        for (String str20 : listL) {
            Q q4 = qA3;
            Q qA5 = q4.a(str20);
            String strB9 = qA5.b(str8);
            String strB10 = qA5.b(str14);
            String strB11 = qA5.b("力度");
            int iHashCode = strB9.hashCode();
            if (iHashCode == 24524) {
                paint2 = paint;
                paint3 = paint10;
                str10 = str10;
                i5 = i14;
                if (strB9.equals("忌")) {
                    paint2.setColor(i3);
                    paint3.setColor(i3);
                }
            } else if (iHashCode != 26435) {
                if (iHashCode != 31108) {
                    if (iHashCode == 31185 && strB9.equals(str9)) {
                        paint2 = paint;
                        paint2.setColor(i4);
                        paint10.setColor(i4);
                    } else {
                        paint2 = paint;
                    }
                    i5 = i14;
                    Q qA6 = q3.a(strB10);
                    q3 = q3;
                    String str21 = str15;
                    itD = qA6.a(str21).d();
                    paint4 = paint2;
                    i6 = 0;
                    while (itD.hasNext()) {
                        it = itD;
                        if (O1.f.a((String) itD.next(), str20)) {
                            break;
                        }
                        i6++;
                        itD = it;
                    }
                    list = list5;
                    int iIndexOf4 = list.indexOf(strB10);
                    arrayList = arrayList4;
                    w2 w2Var29 = (w2) arrayList.get(iIndexOf4);
                    fH = h(1.0f) + (i6 * this.f3486r0) + w2Var29.a();
                    fH2 = h(29.0f) + (2 * this.f3490s0) + w2Var29.b();
                    if (this.f3439d2) {
                        fH2 += h(11.0f);
                    }
                    f11 = fH2;
                    w2Var = (w2) arrayList.get(list.indexOf(this.f3500v0));
                    i7 = this.f3443e2;
                    if (i7 != 0 || i7 == 5 || i7 == 6) {
                        paint5 = paint10;
                        i8 = i3;
                        list2 = list;
                        i14 = i5;
                        str3 = str9;
                        arrayList2 = arrayList;
                        str4 = str2;
                        canvas.drawLine(h(5.0f) + fH, h(5.0f) + f11, (w2Var.a() + this.f3482q0) - h(10.0f), (w2Var.b() + this.p0) - h(27.0f), paint5);
                        if (this.f3439d2) {
                            canvas.drawRect(fH, f11 - h(9.0f), h(10.0f) + fH, h(3.0f) + f11, paint4);
                        } else {
                            canvas.drawRect(fH, f11 - h(10.0f), h(10.0f) + fH, h(13.0f) + f11, paint4);
                        }
                        paint8.setTextSize(h(10.0f));
                        paint8.setColor(-1);
                        canvas2.drawText(strB9, fH, h(1.0f) + f11, paint8);
                        paint8.setTextSize(h(8.0f));
                        if (!this.f3439d2) {
                            canvas2.drawText(strB11, fH, h(11.0f) + f11, paint8);
                        }
                    } else {
                        arrayList2 = arrayList;
                        i14 = i5;
                        str3 = str9;
                        str4 = str2;
                        paint5 = paint10;
                        i8 = i3;
                        list2 = list;
                    }
                    w2 w2Var30 = (w2) arrayList2.get(list2.indexOf(o(this.f3500v0, 5)));
                    paint8.setColor(this.f3390M0);
                    paint8.setTextSize(h(8.0f));
                    canvas2.drawText("宗", (w2Var30.a() + this.f3482q0) - h(10.0f), (w2Var30.b() + this.p0) - h(55.0f), paint8);
                    str10 = str10;
                    qA3 = q4;
                    paint = paint4;
                    str9 = str3;
                    str15 = str21;
                    str2 = str4;
                    list5 = list2;
                    i3 = i8;
                    paint10 = paint5;
                    arrayList4 = arrayList2;
                    i13 = i13;
                } else {
                    paint2 = paint;
                    paint3 = paint10;
                    if (strB9.equals(str10)) {
                        str10 = str10;
                        i5 = i14;
                        paint2.setColor(i5);
                        paint3.setColor(i5);
                    } else {
                        paint10 = paint3;
                    }
                }
                i5 = i14;
                Q qA7 = q3.a(strB10);
                q3 = q3;
                String str22 = str15;
                itD = qA7.a(str22).d();
                paint4 = paint2;
                i6 = 0;
                while (itD.hasNext()) {
                    it = itD;
                    if (O1.f.a((String) itD.next(), str20)) {
                        break;
                        break;
                    } else {
                        i6++;
                        itD = it;
                    }
                }
                list = list5;
                int iIndexOf5 = list.indexOf(strB10);
                arrayList = arrayList4;
                w2 w2Var210 = (w2) arrayList.get(iIndexOf5);
                fH = h(1.0f) + (i6 * this.f3486r0) + w2Var210.a();
                fH2 = h(29.0f) + (2 * this.f3490s0) + w2Var210.b();
                if (this.f3439d2) {
                    fH2 += h(11.0f);
                }
                f11 = fH2;
                w2Var = (w2) arrayList.get(list.indexOf(this.f3500v0));
                i7 = this.f3443e2;
                if (i7 != 0) {
                    paint5 = paint10;
                    i8 = i3;
                    list2 = list;
                    i14 = i5;
                    str3 = str9;
                    arrayList2 = arrayList;
                    str4 = str2;
                    canvas.drawLine(h(5.0f) + fH, h(5.0f) + f11, (w2Var.a() + this.f3482q0) - h(10.0f), (w2Var.b() + this.p0) - h(27.0f), paint5);
                    if (this.f3439d2) {
                        canvas.drawRect(fH, f11 - h(9.0f), h(10.0f) + fH, h(3.0f) + f11, paint4);
                    } else {
                        canvas.drawRect(fH, f11 - h(10.0f), h(10.0f) + fH, h(13.0f) + f11, paint4);
                    }
                    paint8.setTextSize(h(10.0f));
                    paint8.setColor(-1);
                    canvas2.drawText(strB9, fH, h(1.0f) + f11, paint8);
                    paint8.setTextSize(h(8.0f));
                    if (!this.f3439d2) {
                        canvas2.drawText(strB11, fH, h(11.0f) + f11, paint8);
                    }
                } else {
                    paint5 = paint10;
                    i8 = i3;
                    list2 = list;
                    i14 = i5;
                    str3 = str9;
                    arrayList2 = arrayList;
                    str4 = str2;
                    canvas.drawLine(h(5.0f) + fH, h(5.0f) + f11, (w2Var.a() + this.f3482q0) - h(10.0f), (w2Var.b() + this.p0) - h(27.0f), paint5);
                    if (this.f3439d2) {
                        canvas.drawRect(fH, f11 - h(9.0f), h(10.0f) + fH, h(3.0f) + f11, paint4);
                    } else {
                        canvas.drawRect(fH, f11 - h(10.0f), h(10.0f) + fH, h(13.0f) + f11, paint4);
                    }
                    paint8.setTextSize(h(10.0f));
                    paint8.setColor(-1);
                    canvas2.drawText(strB9, fH, h(1.0f) + f11, paint8);
                    paint8.setTextSize(h(8.0f));
                    if (!this.f3439d2) {
                        canvas2.drawText(strB11, fH, h(11.0f) + f11, paint8);
                    }
                }
                w2 w2Var31 = (w2) arrayList2.get(list2.indexOf(o(this.f3500v0, 5)));
                paint8.setColor(this.f3390M0);
                paint8.setTextSize(h(8.0f));
                canvas2.drawText("宗", (w2Var31.a() + this.f3482q0) - h(10.0f), (w2Var31.b() + this.p0) - h(55.0f), paint8);
                str10 = str10;
                qA3 = q4;
                paint = paint4;
                str9 = str3;
                str15 = str22;
                str2 = str4;
                list5 = list2;
                i3 = i8;
                paint10 = paint5;
                arrayList4 = arrayList2;
                i13 = i13;
            } else {
                paint2 = paint;
                paint3 = paint10;
                str10 = str10;
                i5 = i14;
                if (strB9.equals(str2)) {
                    paint2.setColor(i13);
                    paint3.setColor(i13);
                }
            }
            paint10 = paint3;
            i13 = i13;
            q3 = q3;
            Q qA8 = q3.a(strB10);
            q3 = q3;
            String str23 = str15;
            itD = qA8.a(str23).d();
            paint4 = paint2;
            i6 = 0;
            while (itD.hasNext()) {
                it = itD;
                if (O1.f.a((String) itD.next(), str20)) {
                    break;
                    break;
                } else {
                    i6++;
                    itD = it;
                }
            }
            list = list5;
            int iIndexOf6 = list.indexOf(strB10);
            arrayList = arrayList4;
            w2 w2Var211 = (w2) arrayList.get(iIndexOf6);
            fH = h(1.0f) + (i6 * this.f3486r0) + w2Var211.a();
            fH2 = h(29.0f) + (2 * this.f3490s0) + w2Var211.b();
            if (this.f3439d2) {
                fH2 += h(11.0f);
            }
            f11 = fH2;
            w2Var = (w2) arrayList.get(list.indexOf(this.f3500v0));
            i7 = this.f3443e2;
            if (i7 != 0) {
                paint5 = paint10;
                i8 = i3;
                list2 = list;
                i14 = i5;
                str3 = str9;
                arrayList2 = arrayList;
                str4 = str2;
                canvas.drawLine(h(5.0f) + fH, h(5.0f) + f11, (w2Var.a() + this.f3482q0) - h(10.0f), (w2Var.b() + this.p0) - h(27.0f), paint5);
                if (this.f3439d2) {
                    canvas.drawRect(fH, f11 - h(9.0f), h(10.0f) + fH, h(3.0f) + f11, paint4);
                } else {
                    canvas.drawRect(fH, f11 - h(10.0f), h(10.0f) + fH, h(13.0f) + f11, paint4);
                }
                paint8.setTextSize(h(10.0f));
                paint8.setColor(-1);
                canvas2.drawText(strB9, fH, h(1.0f) + f11, paint8);
                paint8.setTextSize(h(8.0f));
                if (!this.f3439d2) {
                    canvas2.drawText(strB11, fH, h(11.0f) + f11, paint8);
                }
            } else {
                paint5 = paint10;
                i8 = i3;
                list2 = list;
                i14 = i5;
                str3 = str9;
                arrayList2 = arrayList;
                str4 = str2;
                canvas.drawLine(h(5.0f) + fH, h(5.0f) + f11, (w2Var.a() + this.f3482q0) - h(10.0f), (w2Var.b() + this.p0) - h(27.0f), paint5);
                if (this.f3439d2) {
                    canvas.drawRect(fH, f11 - h(9.0f), h(10.0f) + fH, h(3.0f) + f11, paint4);
                } else {
                    canvas.drawRect(fH, f11 - h(10.0f), h(10.0f) + fH, h(13.0f) + f11, paint4);
                }
                paint8.setTextSize(h(10.0f));
                paint8.setColor(-1);
                canvas2.drawText(strB9, fH, h(1.0f) + f11, paint8);
                paint8.setTextSize(h(8.0f));
                if (!this.f3439d2) {
                    canvas2.drawText(strB11, fH, h(11.0f) + f11, paint8);
                }
            }
            w2 w2Var32 = (w2) arrayList2.get(list2.indexOf(o(this.f3500v0, 5)));
            paint8.setColor(this.f3390M0);
            paint8.setTextSize(h(8.0f));
            canvas2.drawText("宗", (w2Var32.a() + this.f3482q0) - h(10.0f), (w2Var32.b() + this.p0) - h(55.0f), paint8);
            str10 = str10;
            qA3 = q4;
            paint = paint4;
            str9 = str3;
            str15 = str23;
            str2 = str4;
            list5 = list2;
            i3 = i8;
            paint10 = paint5;
            arrayList4 = arrayList2;
            i13 = i13;
        }
        List list6 = list5;
        ArrayList arrayList6 = arrayList4;
        Paint paint12 = paint10;
        if (this.f3439d2) {
            w2 w2Var33 = (w2) arrayList6.get(list6.indexOf(this.f3500v0));
            w2 w2Var34 = (w2) arrayList6.get(list6.indexOf(o(this.f3500v0, 6)));
            w2 w2Var35 = (w2) arrayList6.get(list6.indexOf(o(this.f3500v0, 4)));
            w2 w2Var36 = (w2) arrayList6.get(list6.indexOf(o(this.f3500v0, 8)));
            if (O1.f.a(this.f3500v0, "子")) {
                float f12 = 2;
                fA20 = (this.f3482q0 / f12) + w2Var33.a();
                fB2 = w2Var33.b();
                fA21 = (this.f3482q0 / f12) + w2Var34.a();
                fB3 = w2Var34.b() + this.p0;
                fA22 = w2Var35.a() + this.f3482q0;
                fB = (this.p0 / f12) + w2Var35.b();
                fA23 = w2Var36.a();
                fB21 = w2Var36.b() + this.p0;
            } else {
                if (O1.f.a(this.f3500v0, "丑")) {
                    f9 = 2;
                    fA16 = (this.f3482q0 / f9) + w2Var33.a();
                    fB17 = w2Var33.b();
                    fA17 = (this.f3482q0 / f9) + w2Var34.a();
                    fB18 = w2Var34.b() + this.p0;
                    fA18 = w2Var35.a() + this.f3482q0;
                    fB19 = w2Var35.b() + this.p0;
                    fA19 = w2Var36.a();
                    fB20 = w2Var36.b();
                    f10 = this.p0;
                } else {
                    if (O1.f.a(this.f3500v0, "寅")) {
                        fA12 = w2Var33.a() + this.f3482q0;
                        fB13 = w2Var33.b();
                        fA13 = w2Var34.a();
                        fB14 = w2Var34.b() + this.p0;
                        f7 = 2;
                        fA14 = (this.f3482q0 / f7) + w2Var35.a();
                        fB15 = w2Var35.b() + this.p0;
                        fA15 = w2Var36.a();
                        fB16 = w2Var36.b();
                        f8 = this.p0;
                    } else {
                        if (O1.f.a(this.f3500v0, "卯")) {
                            fA8 = w2Var33.a() + this.f3482q0;
                            float f13 = 2;
                            fB6 = (this.p0 / f13) + w2Var33.b();
                            fA9 = w2Var34.a();
                            fB10 = (this.p0 / f13) + w2Var34.b();
                            fA10 = (this.f3482q0 / f13) + w2Var35.a();
                            fB11 = w2Var35.b() + this.p0;
                            fA11 = w2Var36.a();
                            fB12 = w2Var36.b();
                        } else {
                            if (O1.f.a(this.f3500v0, "辰")) {
                                fA5 = w2Var33.a() + this.f3482q0;
                                float f14 = 2;
                                fB6 = (this.p0 / f14) + w2Var33.b();
                                fA6 = w2Var34.a();
                                fB7 = (this.p0 / f14) + w2Var34.b();
                                fA7 = w2Var35.a();
                                fB8 = w2Var35.b() + this.p0;
                                fA2 = (this.f3482q0 / f14) + w2Var36.a();
                                fB9 = w2Var36.b();
                            } else {
                                if (O1.f.a(this.f3500v0, "巳")) {
                                    fA3 = w2Var33.a() + this.f3482q0;
                                    fB2 = w2Var33.b() + this.p0;
                                    fA4 = w2Var34.a();
                                    fB3 = w2Var34.b();
                                    fA = w2Var35.a();
                                    float f15 = 2;
                                    fB4 = (this.p0 / f15) + w2Var35.b();
                                    fA2 = (this.f3482q0 / f15) + w2Var36.a();
                                    fB5 = w2Var36.b();
                                } else {
                                    if (O1.f.a(this.f3500v0, "午")) {
                                        float f16 = 2;
                                        fA20 = (this.f3482q0 / f16) + w2Var33.a();
                                        fB2 = w2Var33.b() + this.p0;
                                        fA21 = (this.f3482q0 / f16) + w2Var34.a();
                                        fB3 = w2Var34.b();
                                        fA22 = w2Var35.a();
                                        fB = (this.p0 / f16) + w2Var35.b();
                                        fA23 = w2Var36.a() + this.f3482q0;
                                        fB21 = w2Var36.b();
                                    } else if (O1.f.a(this.f3500v0, "未")) {
                                        f9 = 2;
                                        fA16 = (this.f3482q0 / f9) + w2Var33.a();
                                        fB17 = w2Var33.b() + this.p0;
                                        fA17 = (this.f3482q0 / f9) + w2Var34.a();
                                        fB18 = w2Var34.b();
                                        fA18 = w2Var35.a();
                                        fB19 = w2Var35.b();
                                        fA19 = w2Var36.a() + this.f3482q0;
                                        fB20 = w2Var36.b();
                                        f10 = this.p0;
                                    } else if (O1.f.a(this.f3500v0, "申")) {
                                        fA12 = w2Var33.a();
                                        fB13 = w2Var33.b() + this.p0;
                                        fA13 = w2Var34.a() + this.f3482q0;
                                        fB14 = w2Var34.b();
                                        f7 = 2;
                                        fA14 = (this.f3482q0 / f7) + w2Var35.a();
                                        fB15 = w2Var35.b();
                                        fA15 = w2Var36.a() + this.f3482q0;
                                        fB16 = w2Var36.b();
                                        f8 = this.p0;
                                    } else if (O1.f.a(this.f3500v0, "酉")) {
                                        fA8 = w2Var33.a();
                                        float f17 = 2;
                                        fB6 = (this.p0 / f17) + w2Var33.b();
                                        fA9 = w2Var34.a() + this.f3482q0;
                                        fB10 = (this.p0 / f17) + w2Var34.b();
                                        fA10 = (this.f3482q0 / f17) + w2Var35.a();
                                        fB11 = w2Var35.b();
                                        fA11 = w2Var36.a() + this.f3482q0;
                                        fB12 = w2Var36.b() + this.p0;
                                    } else if (O1.f.a(this.f3500v0, "戌")) {
                                        fA5 = w2Var33.a();
                                        float f18 = 2;
                                        fB6 = (this.p0 / f18) + w2Var33.b();
                                        fA6 = w2Var34.a() + this.f3482q0;
                                        fB7 = (this.p0 / f18) + w2Var34.b();
                                        fA7 = w2Var35.a() + this.f3482q0;
                                        fB8 = w2Var35.b();
                                        fA2 = (this.f3482q0 / f18) + w2Var36.a();
                                        fB9 = w2Var36.b() + this.p0;
                                    } else if (O1.f.a(this.f3500v0, "亥")) {
                                        fA3 = w2Var33.a();
                                        fB2 = w2Var33.b();
                                        fA4 = w2Var34.a() + this.f3482q0;
                                        fB3 = w2Var34.b() + this.p0;
                                        fA = w2Var35.a() + this.f3482q0;
                                        float f19 = 2;
                                        fB4 = (this.p0 / f19) + w2Var35.b();
                                        fA2 = (this.f3482q0 / f19) + w2Var36.a();
                                        fB5 = w2Var36.b() + this.p0;
                                    } else {
                                        f = 0.0f;
                                        f3 = 0.0f;
                                        fA = 0.0f;
                                        f4 = 0.0f;
                                        fB = 0.0f;
                                        fA2 = 0.0f;
                                        f5 = 0.0f;
                                        f6 = 0.0f;
                                    }
                                    paint12.setColor(this.f3395O0);
                                    canvas.drawLine(f6, f4, f, f3, paint12);
                                    canvas.drawLine(f6, f4, fA, fB, paint12);
                                    canvas.drawLine(f6, f4, fA2, f5, paint12);
                                    canvas.drawLine(fA, fB, fA2, f5, paint12);
                                }
                                f5 = fB5;
                                f6 = fA3;
                                f = fA4;
                                fB = fB4;
                                f4 = fB2;
                                f3 = fB3;
                                paint12.setColor(this.f3395O0);
                                canvas.drawLine(f6, f4, f, f3, paint12);
                                canvas.drawLine(f6, f4, fA, fB, paint12);
                                canvas.drawLine(f6, f4, fA2, f5, paint12);
                                canvas.drawLine(fA, fB, fA2, f5, paint12);
                            }
                            fA = fA7;
                            fB = fB8;
                            f5 = fB9;
                            f6 = fA5;
                            f3 = fB7;
                            f = fA6;
                            f4 = fB6;
                            paint12.setColor(this.f3395O0);
                            canvas.drawLine(f6, f4, f, f3, paint12);
                            canvas.drawLine(f6, f4, fA, fB, paint12);
                            canvas.drawLine(f6, f4, fA2, f5, paint12);
                            canvas.drawLine(fA, fB, fA2, f5, paint12);
                        }
                        fA2 = fA11;
                        f5 = fB12;
                        f6 = fA8;
                        f3 = fB10;
                        fA = fA10;
                        f = fA9;
                        fB = fB11;
                        f4 = fB6;
                        paint12.setColor(this.f3395O0);
                        canvas.drawLine(f6, f4, f, f3, paint12);
                        canvas.drawLine(f6, f4, fA, fB, paint12);
                        canvas.drawLine(f6, f4, fA2, f5, paint12);
                        canvas.drawLine(fA, fB, fA2, f5, paint12);
                    }
                    float f20 = (f8 / f7) + fB16;
                    fB = fB15;
                    f6 = fA12;
                    f = fA13;
                    f5 = f20;
                    f3 = fB14;
                    fA2 = fA15;
                    fA = fA14;
                    f4 = fB13;
                    paint12.setColor(this.f3395O0);
                    canvas.drawLine(f6, f4, f, f3, paint12);
                    canvas.drawLine(f6, f4, fA, fB, paint12);
                    canvas.drawLine(f6, f4, fA2, f5, paint12);
                    canvas.drawLine(fA, fB, fA2, f5, paint12);
                }
                float f21 = (f10 / f9) + fB20;
                fA = fA18;
                f6 = fA16;
                f = fA17;
                f5 = f21;
                f4 = fB17;
                f3 = fB18;
                fA2 = fA19;
                fB = fB19;
                paint12.setColor(this.f3395O0);
                canvas.drawLine(f6, f4, f, f3, paint12);
                canvas.drawLine(f6, f4, fA, fB, paint12);
                canvas.drawLine(f6, f4, fA2, f5, paint12);
                canvas.drawLine(fA, fB, fA2, f5, paint12);
            }
            fA2 = fA23;
            f5 = fB21;
            fA = fA22;
            f6 = fA20;
            f = fA21;
            f4 = fB2;
            f3 = fB3;
            paint12.setColor(this.f3395O0);
            canvas.drawLine(f6, f4, f, f3, paint12);
            canvas.drawLine(f6, f4, fA, fB, paint12);
            canvas.drawLine(f6, f4, fA2, f5, paint12);
            canvas.drawLine(fA, fB, fA2, f5, paint12);
        }
        paint8.setColor(this.f3392N0);
        paint8.setTextSize(h(11.0f));
        if (!O1.f.a(this.l1, this.f3500v0) || this.f3469m0 != this.f3394O) {
            Iterator itD4 = qA2.a("所有元素列表").d();
            if (this.f3439d2) {
                this.f3470m1 = this.f3495t1 + "笔记提醒(传统内容来源于网络，未经过验证，请酌情参考)：\r\n" + b(itD4);
            } else {
                this.f3470m1 = this.f3495t1 + "笔记提醒(需抽象提取象意应用在当下，切不可生搬硬套)：\r\n" + b(itD4);
            }
            this.l1 = this.f3500v0;
            this.f3469m0 = this.f3394O;
        }
        TextPaint textPaint = this.f3481q;
        textPaint.setColor(this.f3392N0);
        textPaint.setTextSize(h(12.0f));
        StringBuilder sb = new StringBuilder();
        sb.append(this.f3470m1.hashCode());
        sb.append('|');
        sb.append(this.f3473n1);
        sb.append('|');
        sb.append(getWidth());
        sb.append('|');
        sb.append(getHeight());
        String string2 = sb.toString();
        if (!O1.f.a(string2, this.f3356A)) {
            this.f3356A = string2;
            int iH = (int) ((4 * this.f3482q0) / h(12.0f));
            int height = ((int) (((double) (getHeight() - h(500.0f))) / (((double) h(12.0f)) * 1.2d))) - 4;
            String string3 = "";
            for (String str24 : d.i0(this.f3470m1, new String[]{"\r\n"})) {
                if (str24.length() != 0) {
                    int i15 = 0;
                    for (int length = str24.length(); length > iH; length -= iH) {
                        StringBuilder sbA2 = e.a(string3);
                        int i16 = i15 * iH;
                        i15++;
                        String strSubstring2 = str24.substring(i16, i15 * iH);
                        O1.f.d(str, strSubstring2);
                        string3 = B.g.n(sbA2, strSubstring2, "\r\n");
                    }
                    StringBuilder sbA3 = e.a(string3);
                    String strSubstring3 = str24.substring(i15 * iH, str24.length());
                    O1.f.d(str, strSubstring3);
                    sbA3.append(strSubstring3);
                    sbA3.append("\r\n");
                    string3 = sbA3.toString();
                }
            }
            String strX = "";
            int i17 = 1;
            for (String str25 : d.i0(string3, new String[]{"\r\n"})) {
                if (i17 > this.f3473n1 * height && str25.length() > 0) {
                    strX = B.g.x(strX, str25, "\r\n");
                }
                int i18 = this.f3473n1;
                if (i17 > (i18 + 1) * height && i18 != -1) {
                    break;
                }
                i17++;
            }
            String str26 = strX;
            if (str26.length() == 0) {
                this.f3473n1 = 0;
                this.f3356A = "";
                invalidate();
            }
            this.f3359B = ((float) getHeight()) > h(563.0f) ? new StaticLayout(str26, textPaint, getWidth() - 15, Layout.Alignment.ALIGN_NORMAL, 1.2f, 0.0f, true) : null;
        }
        StaticLayout staticLayout = this.f3359B;
        if (staticLayout != null) {
            canvas.save();
            canvas2.translate(h(5.0f), h(500.0f));
            staticLayout.draw(canvas2);
            canvas.restore();
            canvas2.drawText(String.valueOf(this.f3473n1 + 1), getWidth() - h(18.0f), getHeight() - h(18.0f), paint8);
        }
    }