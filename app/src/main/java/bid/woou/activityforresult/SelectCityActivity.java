package bid.woou.activityforresult;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectCityActivity extends ExpandableListActivity {
    // 定义省份数组
    private String[] provinces = new String[]{
            "广东","广西","湖南"
    };
    private  String[][] cities = new String[][]{
            { "广州", "深圳", "珠海", "中山" },
            { "桂林", "柳州", "南宁", "北海" },
            { "长沙", "岳阳", "衡阳", "株洲" }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            // 获取指定组位置、指定子列表项处的子列表项数据

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return cities[groupPosition][childPosition];
            }

            @Override
            public long getChildId(int i, int i1) {
                return i1;
            }

            @Override
            public int getChildrenCount(int i) {
                return cities[i].length;
            }

            private TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(SelectCityActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36,0,0,0);
                textView.setTextSize(20);
                return textView;

            }

            // 该方法决定每个子选项的外观

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(i,i1).toString());
                return textView;
            }

            // 获取指定组位置处的组数据
            @Override
            public Object getGroup(int i) {
                return provinces[i];
            }

            @Override
            public int getGroupCount() {
                return provinces.length;
            }

            @Override
            public long getGroupId(int i) {
                return i;
            }

            // 该方法决定每个组选项的外观
            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                LinearLayout l1 = new LinearLayout(SelectCityActivity.this);
                l1.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo = new ImageView(SelectCityActivity.this);
                l1.addView(logo);
                TextView textView = getTextView();
                textView.setText(getGroup(i).toString());
                l1.addView(textView);
                return l1;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }
        };
        // 设置该窗口显示列表
        setListAdapter(adapter);
        getExpandableListView().setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {

                    @Override
                    public boolean onChildClick(ExpandableListView parent,
                                                View view, int i, int i1, long l) {
                        // 获取启动该Activity之前的Intent
                        Intent intent = getIntent();
                        intent.putExtra("city",
                                cities[i][i1]);
                        SelectCityActivity.this.setResult(0,intent);
                        SelectCityActivity.this.finish();
                        return false;
                    }
                }
        );
    }
}
