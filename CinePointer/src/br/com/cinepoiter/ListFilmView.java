package br.com.cinepoiter;


import java.util.ArrayList;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListFilmView extends ListActivity implements OnItemClickListener{
   
    private ProgressDialog m_ProgressDialog = null;
    private ArrayList<Filme> m_orders = null;
    private FilmAdapter m_adapter;
    private Runnable viewOrders;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.getListView().setOnItemClickListener(this);
        m_orders = new ArrayList<Filme>();
        this.m_adapter = new FilmAdapter(this, R.layout.row, m_orders);
        setListAdapter(this.m_adapter);
       
        viewOrders = new Runnable(){
            public void run() {
                getOrders();
            }
        };
        Thread thread =  new Thread(null, viewOrders, "MagentoBackground");
        thread.start();
        m_ProgressDialog = ProgressDialog.show(ListFilmView.this,    
              "Please wait...", "Retrieving data ...", true);
    }
    private Runnable returnRes = new Runnable() {
        public void run() {
            if(m_orders != null && m_orders.size() > 0){
                m_adapter.notifyDataSetChanged();
                for(int i=0;i<m_orders.size();i++)
                m_adapter.add(m_orders.get(i));
            }
            m_ProgressDialog.dismiss();
            m_adapter.notifyDataSetChanged();
        }
    };
    private void getOrders(){
          try{
              m_orders = new ArrayList<Filme>();
              Filme f1 = new Filme();
              f1.setNome("Piratas do Caribe");
              f1.setGenero("Aventura");
              Filme f2 = new Filme();
              f2.setNome("Piratas do Caribe");
              f2.setGenero("Aventura");
              Filme f3 = new Filme();
              f3.setNome("Piratas do Caribe");
              f3.setGenero("Aventura");
              Filme f4 = new Filme();
              f4.setNome("Piratas do Caribe");
              f4.setGenero("Aventura");
              Filme f5 = new Filme();
              f5.setNome("Piratas do Caribe");
              f5.setGenero("Aventura");
              m_orders.add(f1);
              m_orders.add(f2);
              m_orders.add(f3);
              m_orders.add(f4);
              m_orders.add(f5);
              Thread.sleep(2000);
              Log.i("ARRAY", ""+ m_orders.size());
            } catch (Exception e) {
              Log.e("BACKGROUND_PROC", e.getMessage());
            }
            runOnUiThread(returnRes);
        }
    private class FilmAdapter extends ArrayAdapter<Filme> {

        private ArrayList<Filme> items;

        public FilmAdapter(Context context, int textViewResourceId, ArrayList<Filme> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.row, null);
                }
                Filme f = items.get(position);
                if (f != null) {
                        TextView tt = (TextView) v.findViewById(R.id.toptext);
                        TextView bt = (TextView) v.findViewById(R.id.bottomtext);
                        if (tt != null) {
                              tt.setText("Nome: "+f.getNome());                            
                              }
                        if(bt != null){
                              bt.setText("Gênero: "+ f.getGenero());
                        }
                }
                return v;
        }
}
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent i = new Intent(this,DetalhesActivity.class);
		startActivity(i);
	}
}