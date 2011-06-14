package br.com.cinepoiter;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class ItemsList extends ListActivity { 
	private ItemsAdapter adapter; 
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.main); 
		this.adapter = new ItemsAdapter(this, R.layout.film_view, ItemManager.getLoadedItems()); 
		setListAdapter(this.adapter); 
	} 
	private class ItemsAdapter extends ArrayAdapter<Filme> { 
		private Filme[] items; 
		public ItemsAdapter(Context context, int textViewResourceId,Filme[] items) { 
			super(context, textViewResourceId, items); 
			this.items = items; 
		} 
		@Override 
		public View getView(int position, View convertView, ViewGroup parent) { 
			View v = convertView; 
			if (v == null) { 
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
				v = vi.inflate(R.layout.film_view, null); 
			} 
			Filme it = items[position]; 
			if (it != null) { 
				ImageView iv = (ImageView) v.findViewById(R.id.list_item_image); 
				if (iv != null) { 
					iv.setImageDrawable(it.getBanner()); 
				} 
			} 
			return v; 
		} 
	} 
	@Override 
	protected void onListItemClick(ListView l, View v, int position, long id) { 
		this.adapter.getItem(position).click(this.getApplicationContext()); 
	} 
} 