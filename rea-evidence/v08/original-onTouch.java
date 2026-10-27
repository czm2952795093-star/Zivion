    /* JADX WARN: Type inference failed for: r0v43, types: [N1.a, O1.g] */
    /* JADX WARN: Type inference failed for: r2v42, types: [N1.p, O1.g] */
    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ArrayList arrayList;
        O1.f.e("event", motionEvent);
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            float x3 = motionEvent.getX();
            float y3 = motionEvent.getY();
            while (true) {
                arrayList = this.f3362C;
                if (arrayList.size() < this.f3372G) {
                    break;
                }
                arrayList.remove(0);
            }
            arrayList.add(new u2(x3, y3, System.currentTimeMillis()));
            if (!this.E) {
                this.E = true;
                postOnAnimation(this.f3370F);
            }
            float f = this.f3482q0;
            float f3 = this.f3476o0;
            float f4 = f3 + f;
            float f5 = this.p0;
            float f6 = f3 + f5;
            float f7 = 3;
            float f8 = (f * f7) + f3;
            float f9 = (f7 * f5) + f3;
            this.f3503w0 = x2 > f4 && y2 > f6 && x2 < f8 && y2 < f9;
            if (y2 > h(500.0f)) {
                if (x2 > this.f3482q0) {
                    this.f3473n1++;
                } else {
                    Intent intent = new Intent(getContext(), (Class<?>) xianshibiji.class);
                    intent.putExtra("txt", this.f3470m1);
                    getContext().startActivity(intent);
                }
            } else if (!this.f3503w0) {
                int i3 = 0;
                for (w2 w2Var : this.f3506x0) {
                    float f10 = w2Var.f7646a;
                    if (x2 > f10) {
                        float f11 = w2Var.f7647b;
                        if (y2 > f11 && x2 < f10 + this.f3482q0 && y2 < f11 + this.p0) {
                            this.f3500v0 = (String) this.f3360B0.get(i3);
                            this.c2 = true;
                            this.f3473n1 = 0;
                            break;
                        }
                    }
                    i3++;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.f3394O);
            sb.append('|');
            sb.append(getWidth());
            sb.append('x');
            sb.append(getHeight());
            sb.append('|');
            sb.append(f4);
            sb.append('|');
            sb.append(f6);
            sb.append('|');
            sb.append(f8);
            sb.append('|');
            sb.append(f9);
            String string = sb.toString();
            List<v2> listO = this.h3;
            if (listO == null || !O1.f.a(string, this.i3)) {
                listO = j.O(new v2("点了性别", new A2(this, 1), new y2(this, 10)), new v2("中宫右上角·随机排盘", new z2(f4, this, f6, 5), new y2(this, 15)), new v2("中宫左上角·换日", new z2(f4, f6, this, 6), new y2(this, 16)), new v2("时间区域·更新到当前时间(竖屏)", new A2(this, 3), new y2(this, 17)), new v2("时间区域·更新到当前时间(横屏)", new A2(this, 4), new y2(this, 0)), new v2("AI解盘", new z2(f4, this, f6, 0), new y2(this, 1)), new v2("复制盘面信息", new z2(f4, f6, this, 1), new y2(this, 2)), new v2("中心区域·退出程序", new A2(this, 0), new y2(this, 3)), new v2("中心右侧·时辰加一(竖屏)", new B2(this, f4, 0), new y2(this, 4)), new v2("中心右侧·时辰加一(横屏)", new B2(this, f4, 1), new y2(this, 5)), new v2("中心左侧·时辰减一", new B2(f4, this), new y2(this, 6)), new v2("中心左侧·隐藏中心时间", new B2(this, f4, 3), new y2(this, 7)), new v2("切换年月日时星", new z2(f4, f6, this, 2), new y2(this, 8)), new v2("流盘区域·切换流盘模式", new A2(this, 2), new y2(this, 9)), new v2("减号区域·减岁数或打开输入页", new z2(f4, this, f6, 3), new y2(this, 11)), new v2("加号区域·加岁数或打开更多", new z2(this, f4, f6), new y2(this, 12)), new v2("横屏·更多", new B2(this, f4, 4), new y2(this, 13)), new v2("中宫其他区域·打开输入页", new C2(f4, f6, this, f8, f9), new y2(this, 14)));
                this.h3 = listO;
                this.i3 = string;
            }
            for (v2 v2Var : listO) {
                if (((Boolean) v2Var.f7633a.c(Float.valueOf(x2), Float.valueOf(y2))).booleanValue()) {
                    v2Var.f7634b.a();
                    break;
                }
            }
            invalidate();
        } else if (action == 1) {
            super.performClick();
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }