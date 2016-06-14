package com.csdn.gridview;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 网格视图（GridView)
 * @author devilwwj
 *
 */
public class MainActivity extends Activity {
	private GridView gridview;
	
	 //展示图片  
    private Integer[] mThumbIds = {  
            R.drawable.sample_2, R.drawable.sample_3,  
            R.drawable.sample_4, R.drawable.sample_5,  
            R.drawable.sample_6, R.drawable.sample_7,  
            R.drawable.sample_0, R.drawable.sample_1,  
            R.drawable.sample_2, R.drawable.sample_3,  
            R.drawable.sample_4, R.drawable.sample_5,  
            R.drawable.sample_6, R.drawable.sample_7,  
            R.drawable.sample_0, R.drawable.sample_1,  
            R.drawable.sample_2, R.drawable.sample_3,  
            R.drawable.sample_4, R.drawable.sample_5,  
            R.drawable.sample_6, R.drawable.sample_7  
    };  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        gridview = (GridView) this.findViewById(R.id.gridview);
        
        // 设置gridView的adapter
        gridview.setAdapter(new ImageAdapter());
        
        
        
    }
    
    private class ImageAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mThumbIds.length;
		}

		@Override
		public Object getItem(int position) {
			return mThumbIds[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.grid_view_item, null);
				holder = new ViewHolder();
				holder.img = (ImageView) convertView.findViewById(R.id.itemImage);
				holder.title = (TextView) convertView.findViewById(R.id.itemText);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.img.setImageResource(mThumbIds[position]);
			holder.title.setText("狗" + position);
			
			
			return convertView;
		}
		
		private class ViewHolder {
			private ImageView img;
			private TextView title;
		}
    	
    }
    
    
    
}
