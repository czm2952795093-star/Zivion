    /* JADX WARN: Code duplicated, block: B:100:0x0494  */
    /* JADX WARN: Code duplicated, block: B:107:0x04af  */
    /* JADX WARN: Code duplicated, block: B:114:0x04cc  */
    /* JADX WARN: Code duplicated, block: B:118:0x04e0  */
    /* JADX WARN: Code duplicated, block: B:121:0x04f3  */
    /* JADX WARN: Code duplicated, block: B:122:0x04f6  */
    public final void g(Canvas canvas) {
        float f;
        float f3;
        float f4;
        float f5;
        float f6;
        List list;
        float f7;
        float f8;
        float f9;
        float f10;
        String str;
        int i3;
        String str2;
        Paint paint = this.f3454i;
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setUnderlineText(false);
        paint.setColor(this.f3430b0);
        paint.setFakeBoldText(this.f3434c0);
        paint.setTypeface(this.f3437d0);
        Paint paint2 = this.f3465l;
        float f11 = this.f3415V;
        float f12 = this.f3418W;
        if (this.f3422X1.length() == 0) {
            this.f3422X1 = this.f3427a0;
        }
        Q qA = this.f3420W1.a(this.f3422X1);
        int i4 = Integer.parseInt(qA.b("起始"));
        this.f3424Y1 = qA.b("宫干");
        if (this.f3512z1 > i4 + 9) {
            for (int i5 = 0; i5 < 12; i5++) {
                String strO = o(this.f3422X1, 1);
                this.f3422X1 = strO;
                Q qA2 = this.f3420W1.a(strO);
                this.f3424Y1 = qA2.b("宫干");
                int i6 = Integer.parseInt(qA2.b("起始"));
                int i7 = this.f3512z1;
                if (i7 >= i6 && i7 <= i6 + 9) {
                    break;
                }
            }
        }
        ArrayList arrayList = this.y0;
        String str3 = this.f3422X1;
        List list2 = this.f3360B0;
        float f13 = 3;
        float f14 = 8;
        float f15 = (((((w2) arrayList.get(list2.indexOf(str3))).f7646a - f11) * f13) / f14) + f11;
        float f16 = (((((w2) arrayList.get(list2.indexOf(this.f3422X1))).f7647b - f12) * f13) / f14) + f12;
        int i8 = this.f3431b1;
        paint2.setColor(i8);
        paint2.setStrokeWidth(h(2.0f));
        float f17 = (((((w2) arrayList.get(this.e3)).f7646a - f11) * f13) / f14) + f11;
        float f18 = (((((w2) arrayList.get(this.e3)).f7647b - f12) * f13) / f14) + f12;
        if (this.f3411T1) {
            f = f16;
            f3 = f15;
            f4 = f17;
            canvas.drawLine(f11, f12, f3, f, paint2);
        } else {
            f = f16;
            f3 = f15;
            f4 = f17;
            canvas.drawLine(f11, f12, f4, f18, paint2);
        }
        Paint.Align align = Paint.Align.CENTER;
        paint.setTextAlign(align);
        paint.setTextSize(h(8.0f));
        boolean z2 = this.f3411T1;
        List list3 = this.f3357A0;
        if (z2) {
            canvas.drawText(this.f3424Y1, f3, f, paint);
        } else {
            canvas.drawText((String) list3.get(this.d3), f4, f18, paint);
        }
        float f19 = 5;
        float f20 = (((((w2) arrayList.get(list2.indexOf(this.f3426Z1))).f7646a - f11) * f19) / f14) + f11;
        float f21 = (((((w2) arrayList.get(list2.indexOf(this.f3426Z1))).f7647b - f12) * f19) / f14) + f12;
        int i9 = this.f3428a1;
        paint2.setColor(i9);
        paint2.setStrokeWidth(h(1.0f));
        float f22 = (((((w2) arrayList.get(this.c3)).f7646a - f11) * f19) / f14) + f11;
        float f23 = (((((w2) arrayList.get(this.c3)).f7647b - f12) * f19) / f14) + f12;
        if (this.f3411T1) {
            f5 = f21;
            list = list3;
            f6 = f22;
            canvas.drawLine(f11, f12, f20, f5, paint2);
        } else {
            f5 = f21;
            f6 = f22;
            list = list3;
            canvas.drawLine(f11, f12, f6, f23, paint2);
        }
        paint.setTextAlign(align);
        paint.setTextSize(h(8.0f));
        if (this.f3411T1) {
            f7 = f5;
            f8 = f20;
            canvas.drawText(this.f3429a2, f8, f7, paint);
        } else {
            f7 = f5;
            f8 = f20;
            canvas.drawText((String) list.get(this.b3), f6, f23, paint);
        }
        int i10 = this.f3414U1;
        ArrayList arrayList2 = this.f3506x0;
        if (i10 > 0) {
            String str4 = this.f3397P + '|' + this.B2 + '|' + this.G2 + '|' + this.H2 + '|' + this.I2;
            O1.f.d("toString(...)", str4);
            if (this.f3403R != null && this.f3409T == getWidth() && this.f3412U == getHeight() && O1.f.a(this.f3406S, str4)) {
                f9 = f8;
                f10 = f7;
                str = "";
            } else {
                Picture picture = new Picture();
                Canvas canvasBeginRecording = picture.beginRecording(getWidth(), getHeight());
                O1.f.d("beginRecording(...)", canvasBeginRecording);
                String str5 = this.f3422X1;
                List listO = j.O("父", "福", "田", "官", "友", "迁", "疾", "财", "子", "夫", "兄", "命");
                Paint paint3 = this.f3479p;
                paint3.setTypeface(O1.f.a(this.U2, "1") ? getJiaguwenTypeface() : null);
                paint3.setTextAlign(Paint.Align.LEFT);
                str = "";
                paint3.setTextSize(h(9.0f));
                paint3.setColor(i8);
                paint3.setFakeBoldText(true);
                int iIndexOf = list2.indexOf(str5);
                int i11 = 1;
                while (true) {
                    if (i11 >= 13) {
                        break;
                    }
                    int i12 = iIndexOf + i11;
                    int i13 = iIndexOf;
                    String str6 = "限" + ((String) listO.get(i11 - 1));
                    w2 w2Var = (w2) arrayList2.get(i12 % 12);
                    canvasBeginRecording.drawText(str6, (w2Var.f7646a + this.f3482q0) - h(39.0f), (w2Var.f7647b + this.p0) - h(16.0f), paint3);
                    i11++;
                    iIndexOf = i13;
                    listO = listO;
                }
                if (this.f3414U1 > 1) {
                    String str7 = this.f3426Z1;
                    List listO2 = j.O("父", "福", "田", "官", "友", "迁", "疾", "财", "子", "夫", "兄", "命");
                    paint3.setTypeface(O1.f.a(this.U2, "1") ? getJiaguwenTypeface() : null);
                    paint3.setTextAlign(Paint.Align.LEFT);
                    paint3.setTextSize(h(9.0f));
                    paint3.setColor(i9);
                    paint3.setFakeBoldText(true);
                    int iIndexOf2 = list2.indexOf(str7);
                    int i14 = 1;
                    for (i3 = 13; i14 < i3; i3 = 13) {
                        int i15 = iIndexOf2 + i14;
                        int i16 = iIndexOf2;
                        String str8 = "年" + ((String) listO2.get(i14 - 1));
                        w2 w2Var2 = (w2) arrayList2.get(i15 % 12);
                        canvasBeginRecording.drawText(str8, (w2Var2.f7646a + this.f3482q0) - h(39.0f), (w2Var2.f7647b + this.p0) - h(26.0f), paint3);
                        i14++;
                        iIndexOf2 = i16;
                        listO2 = listO2;
                    }
                    String strO2 = o(this.B2, 1);
                    List listO3 = j.O("正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊");
                    paint3.setTypeface(O1.f.a(this.U2, "1") ? getJiaguwenTypeface() : null);
                    paint3.setTextAlign(Paint.Align.LEFT);
                    paint3.setTextSize(h(8.0f));
                    paint3.setColor(this.f3442e1);
                    paint3.setFakeBoldText(false);
                    int iIndexOf3 = list2.indexOf(strO2);
                    int i17 = 0;
                    while (i17 < 12) {
                        int i18 = iIndexOf3 + i17;
                        StringBuilder sb = new StringBuilder();
                        int i19 = iIndexOf3;
                        sb.append((String) listO3.get(i17));
                        sb.append((char) 26376);
                        String str9 = this.f3429a2;
                        List list4 = listO3;
                        List listO4 = j.O("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸");
                        float f24 = f8;
                        int iHashCode = str9.hashCode();
                        float f25 = f7;
                        if (iHashCode != 19969) {
                            if (iHashCode != 19993) {
                                if (iHashCode != 20057) {
                                    if (iHashCode != 22764) {
                                        if (iHashCode != 24049) {
                                            if (iHashCode != 24218) {
                                                if (iHashCode != 25098) {
                                                    if (iHashCode != 30002) {
                                                        if (iHashCode != 30328) {
                                                            if (iHashCode == 36763 && str9.equals("辛")) {
                                                                str2 = (String) listO4.get((i17 + 6) % 10);
                                                            }
                                                        } else if (str9.equals("癸")) {
                                                            str2 = (String) listO4.get(i17 % 10);
                                                        }
                                                    } else if (str9.equals("甲")) {
                                                        str2 = (String) listO4.get((i17 + 2) % 10);
                                                    }
                                                } else if (str9.equals("戊")) {
                                                    str2 = (String) listO4.get(i17 % 10);
                                                }
                                            } else if (str9.equals("庚")) {
                                                str2 = (String) listO4.get((i17 + 4) % 10);
                                            }
                                        } else if (str9.equals("己")) {
                                            str2 = (String) listO4.get((i17 + 2) % 10);
                                        }
                                    } else if (str9.equals("壬")) {
                                        str2 = (String) listO4.get((i17 + 8) % 10);
                                    }
                                    str2 = str;
                                } else if (str9.equals("乙")) {
                                    str2 = (String) listO4.get((i17 + 4) % 10);
                                } else {
                                    str2 = str;
                                }
                            } else if (str9.equals("丙")) {
                                str2 = (String) listO4.get((i17 + 6) % 10);
                            } else {
                                str2 = str;
                            }
                        } else if (str9.equals("丁")) {
                            str2 = (String) listO4.get((i17 + 8) % 10);
                        } else {
                            str2 = str;
                        }
                        sb.append(str2);
                        String string = sb.toString();
                        w2 w2Var3 = (w2) arrayList2.get(i18 % 12);
                        canvasBeginRecording.drawText(string, (w2Var3.f7646a + this.f3482q0) - h(63.0f), (w2Var3.f7647b + this.p0) - h(12.0f), paint3);
                        i17++;
                        f8 = f24;
                        iIndexOf3 = i19;
                        listO3 = list4;
                        f7 = f25;
                    }
                    f9 = f8;
                    f10 = f7;
                    if (!this.J2) {
                        String strO3 = o(this.B2, Integer.parseInt(this.G2));
                        String strO4 = o(strO3, Integer.parseInt(this.H2) - 1);
                        String strO5 = o(strO4, list2.indexOf(this.I2));
                        List listO5 = j.O("父", "福", "田", "官", "友", "迁", "疾", "财", "子", "夫", "兄", "命");
                        paint3.setTypeface(O1.f.a(this.U2, "1") ? getJiaguwenTypeface() : null);
                        paint3.setTextAlign(Paint.Align.LEFT);
                        paint3.setTextSize(h(9.0f));
                        paint3.setColor(this.f3445f1);
                        paint3.setFakeBoldText(false);
                        int iIndexOf4 = list2.indexOf(strO3);
                        int i20 = 1;
                        while (i20 < 13) {
                            int i21 = iIndexOf4 + i20;
                            int i22 = iIndexOf4;
                            String str10 = "月" + ((String) listO5.get(i20 - 1));
                            w2 w2Var4 = (w2) arrayList2.get(i21 % 12);
                            canvasBeginRecording.drawText(str10, (w2Var4.f7646a + this.f3482q0) - h(39.0f), (w2Var4.f7647b + this.p0) - h(44.0f), paint3);
                            i20++;
                            iIndexOf4 = i22;
                            listO5 = listO5;
                        }
                        List listO6 = j.O("父", "福", "田", "官", "友", "迁", "疾", "财", "子", "夫", "兄", "命");
                        paint3.setTypeface(O1.f.a(this.U2, "1") ? getJiaguwenTypeface() : null);
                        paint3.setTextAlign(Paint.Align.LEFT);
                        paint3.setTextSize(h(9.0f));
                        paint3.setColor(this.g1);
                        paint3.setFakeBoldText(false);
                        int iIndexOf5 = list2.indexOf(strO4);
                        int i23 = 1;
                        while (i23 < 13) {
                            String str11 = "日" + ((String) listO6.get(i23 - 1));
                            w2 w2Var5 = (w2) arrayList2.get((iIndexOf5 + i23) % 12);
                            canvasBeginRecording.drawText(str11, (w2Var5.f7646a + this.f3482q0) - h(39.0f), (w2Var5.f7647b + this.p0) - h(52.0f), paint3);
                            i23++;
                            listO6 = listO6;
                        }
                        List listO7 = j.O("父", "福", "田", "官", "友", "迁", "疾", "财", "子", "夫", "兄", "命");
                        paint3.setTypeface(O1.f.a(this.U2, "1") ? getJiaguwenTypeface() : null);
                        paint3.setTextAlign(Paint.Align.LEFT);
                        paint3.setTextSize(h(9.0f));
                        paint3.setColor(this.f3452h1);
                        paint3.setFakeBoldText(false);
                        int iIndexOf6 = list2.indexOf(strO5);
                        for (int i24 = 1; i24 < 13; i24++) {
                            String str12 = "时" + ((String) listO7.get(i24 - 1));
                            w2 w2Var6 = (w2) arrayList2.get((iIndexOf6 + i24) % 12);
                            canvasBeginRecording.drawText(str12, (w2Var6.f7646a + this.f3482q0) - h(39.0f), (w2Var6.f7647b + this.p0) - h(60.0f), paint3);
                        }
                    }
                } else {
                    f9 = f8;
                    f10 = f7;
                }
                picture.endRecording();
                this.f3403R = picture;
                this.f3409T = getWidth();
                this.f3412U = getHeight();
                this.f3406S = str4;
            }
            Picture picture2 = this.f3403R;
            if (picture2 != null) {
                canvas.drawPicture(picture2);
            }
            if (!this.c2) {
                this.f3500v0 = this.f3422X1;
            }
        } else {
            f9 = f8;
            f10 = f7;
            str = "";
        }
        if (this.f3414U1 > 1) {
            if (!this.c2) {
                this.f3500v0 = this.f3426Z1;
            }
            int i25 = 0;
            while (true) {
                if (i25 >= 12) {
                    i25 = 0;
                    break;
                } else if (O1.f.a(this.f3429a2, this.f3388L0.get(i25))) {
                    break;
                } else {
                    i25++;
                }
            }
            Paint paint4 = this.f3475o;
            paint4.setColor(this.f3390M0);
            canvas.drawLine(f9, f10 - 5.0f, (((w2) arrayList2.get(i25)).f7646a + this.f3482q0) - 36.0f, (((w2) arrayList2.get(i25)).f7647b + this.p0) - 63.0f, paint4);
        }
        String str13 = this.f3500v0 + '|' + this.f3512z1 + '|' + this.f3439d2 + '|' + this.f3480p1.hashCode() + '|' + this.f3495t1.hashCode();
        if (O1.f.a(str13, this.f3505x)) {
            return;
        }
        Q qA3 = this.f3511z0.a("十二宫信息");
        Q qA4 = qA3.a(this.f3500v0);
        String str14 = "宫";
        String strB = qA4.b("宫");
        this.X2 = B.g.w("一、直接描述问题的核心宫位是", strB);
        this.X2 = B.g.n(new StringBuilder(), this.X2, "宫,宫内包括:");
        String str15 = "星列表";
        Iterator itD = qA4.a("星列表").d();
        if (qA4.c("星庙旺")) {
            Q qA5 = qA4.a("星庙旺");
            while (itD.hasNext()) {
                String str16 = (String) itD.next();
                if (qA5.c(str16)) {
                    this.X2 += str16 + ':' + qA5.a(str16).b("庙旺") + (char) 12289;
                } else {
                    this.X2 += str16 + (char) 12289;
                }
            }
        } else {
            while (itD.hasNext()) {
                this.X2 += ((String) itD.next()) + (char) 12289;
            }
        }
        String str17 = "寅";
        if (O1.f.a(this.f3500v0, "寅")) {
            str17 = "子";
        } else if (O1.f.a(this.f3500v0, "卯")) {
            str17 = "丑";
        } else if (O1.f.a(this.f3500v0, "丑")) {
            str17 = "卯";
        } else if (!O1.f.a(this.f3500v0, "子")) {
            str17 = str;
        }
        if (str17.length() > 0) {
            Q qA6 = qA3.a(str17);
            Iterator itD2 = qA6.a("星列表").d();
            if (qA6.c("星庙旺")) {
                Q qA7 = qA6.a("星庙旺");
                while (itD2.hasNext()) {
                    String str18 = (String) itD2.next();
                    if (qA7.c(str18)) {
                        this.X2 += str18 + ':' + qA7.a(str18).b("庙旺") + (char) 12289;
                    } else {
                        this.X2 += str18 + (char) 12289;
                    }
                }
            } else {
                while (itD2.hasNext()) {
                    this.X2 += ((String) itD2.next()) + (char) 12289;
                }
            }
        }
        this.X2 = d.R(this.X2);
        this.X2 = B.g.m(new StringBuilder(), this.X2, (char) 12290);
        if (str17.length() > 0) {
            this.X2 = B.g.n(new StringBuilder(), this.X2, m(strB, qA3.a(str17).b("宫")));
        }
        this.X2 = B.g.n(new StringBuilder(), this.X2, "\n二、核心宫位的四化:");
        Q qA8 = qA4.a("四化");
        Iterator itD3 = qA8.d();
        while (itD3.hasNext()) {
            String str19 = (String) itD3.next();
            Q qA9 = qA8.a(str19);
            String strB2 = qA9.b("支");
            String strB3 = qA9.b(str14);
            String strB4 = qA9.b("力度");
            String str20 = str14;
            String strB5 = qA9.b("化");
            Q qA10 = qA3.a(strB2);
            Iterator itD4 = qA10.a(str15).d();
            Q qA11 = qA10.a("星庙旺");
            Q q3 = qA3;
            String strB6 = qA11.a(str19).b("庙旺");
            String str21 = str15;
            String strM = m(strB, strB3);
            String str22 = strB;
            StringBuilder sb2 = new StringBuilder();
            Q q4 = qA8;
            sb2.append(this.X2);
            sb2.append(str19);
            sb2.append(strB6);
            sb2.append(" 化");
            sb2.append(strB5);
            sb2.append(" 力度");
            sb2.append(strB4);
            sb2.append("%，在");
            sb2.append(strB3);
            sb2.append("宫，");
            sb2.append(strM);
            sb2.append(" 化");
            this.X2 = B.g.n(sb2, strB5, "的宫位内包括:");
            while (itD4.hasNext()) {
                String str23 = (String) itD4.next();
                if (qA11.c(str23)) {
                    this.X2 += str23 + ':' + qA11.a(str19).b("庙旺") + (char) 12289;
                } else {
                    this.X2 += str23 + (char) 12289;
                }
            }
            this.X2 = d.R(this.X2);
            this.X2 = B.g.m(new StringBuilder(), this.X2, (char) 12290);
            str14 = str20;
            qA3 = q3;
            str15 = str21;
            strB = str22;
            qA8 = q4;
        }
        Iterator itD5 = qA4.a("所有元素列表").d();
        ArrayList arrayList3 = new ArrayList();
        while (itD5.hasNext()) {
            arrayList3.add((String) itD5.next());
        }
        HashSet hashSet = new HashSet(arrayList3);
        String strConcat = str;
        for (Map.Entry entry : ((LinkedHashMap) j(false)).entrySet()) {
            String str24 = (String) entry.getKey();
            String str25 = (String) entry.getValue();
            if (hashSet.containsAll(d.i0(str24, new String[]{"#"}))) {
                List listI0 = d.i0(str25, new String[]{"#"});
                StringBuilder sbA = e.a(strConcat);
                String str26 = str;
                sbA.append(l.L(str24, "#", str26));
                sbA.append(':');
                String string2 = sbA.toString();
                if (listI0.size() == 1) {
                    strConcat = B.g.m(e.a(string2), (String) listI0.get(0), (char) 12290);
                } else {
                    Iterator it = listI0.iterator();
                    int i26 = 1;
                    while (it.hasNext()) {
                        string2 = string2 + ((String) it.next()) + (char) 12289;
                        i26++;
                        if (i26 > 5) {
                            break;
                        }
                    }
                    strConcat = d.R(string2).concat("等。");
                }
                str = str26;
            }
        }
        this.Z2 = strConcat;
        this.X2 += "\n三、核心宫位内意思解释举例:" + l.L(l.L(l.L(l.L(l.L(strConcat, " ", str), "\r\n", "。"), "\n", "。"), "|", "、"), ":。", ":");
        int i27 = this.f3512z1;
        if (i27 > 3 && i27 < 100) {
            this.X2 += "四、命主今年的年龄是:" + this.f3512z1;
            String strB7 = this.f3511z0.a("其他信息").b("性别");
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.X2);
            sb3.append("。命主性别是:");
            String strSubstring = strB7.toString().substring(1, 2);
            O1.f.d("substring(...)", strSubstring);
            sb3.append(strSubstring);
            this.X2 = sb3.toString();
        }
        this.f3505x = str13;
    }