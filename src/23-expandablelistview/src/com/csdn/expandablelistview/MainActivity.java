package com.csdn.expandablelistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ExpandableListView的使用
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {
	private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        expandableListView = (ExpandableListView) this.findViewById(R.id.expandableListView);
        // 定义组列表
        List<String> groupList = new ArrayList<String>();
        groupList.add("The group one");
        groupList.add("The group two");
        groupList.add("The group three");
        
        
        // 定义孩子项列表
        List<List<String>> childList = new ArrayList<List<String>>();
        for (int i = 0; i < groupList.size(); i++) {
        	List<String> strings = new ArrayList<String>();
        	strings.add("the child one item" + i);
        	strings.add("the child two item" + i);
        	childList.add(strings);
        }
        
        expandableListView.setAdapter(new ExpandableListAdapter(groupList, childList, this));
        
    }
    
    private class ExpandableListAdapter extends BaseExpandableListAdapter {
    	private List<String> groupList = null;
    	private List<List<String>> childList = null;
    	private Context context;
    	
    	

		public ExpandableListAdapter(List<String> groupList,
				List<List<String>> childList, Context context) {
			super();
			this.groupList = groupList;
			this.childList = childList;
			this.context = context;
		}

		@Override
		public int getGroupCount() {
			return groupList.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return childList.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return groupList.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return childList.get(groupPosition).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			GroupViewHolder groupViewHolder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.group_list_item, null);
				
				groupViewHolder = new GroupViewHolder();
				groupViewHolder.groupText = (TextView) convertView.findViewById(R.id.group_item_text);
				
				convertView.setTag(groupViewHolder);
				
				
			} else {
				groupViewHolder = (GroupViewHolder) convertView.getTag();
			}
			
			
			groupViewHolder.groupText.setText(groupList.get(groupPosition));
			
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			ChildViewHolder childViewHolder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(R.layout.child_list_item, null);
				
				childViewHolder = new ChildViewHolder();
				
				childViewHolder.childImg = (ImageView) convertView.findViewById(R.id.child_img);
				childViewHolder.childText = (TextView) convertView.findViewById(R.id.child_item_text);
				
				convertView.setTag(childViewHolder);
			} else {
				childViewHolder = (ChildViewHolder) convertView.getTag();
			}
			
			childViewHolder.childText.setText(childList.get(groupPosition).get(childPosition));
			
			
			
			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
		
		private class GroupViewHolder {
			TextView groupText;
		}
		
		private class ChildViewHolder {
			ImageView childImg;
			TextView childText;
		}
    	
    }
    
    

}
