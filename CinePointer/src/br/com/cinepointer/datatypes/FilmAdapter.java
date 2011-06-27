package br.com.cinepointer.datatypes;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.cinepointer.R;

public class FilmAdapter extends BaseAdapter {

    private Context context;
    private List<Filme> filmList;

   
    public FilmAdapter(Context context, List<Filme>filmlist){
        this.context = context;
        this.filmList = filmlist;
    }

    public int getCount() {
        return filmList.size();
    }
    
    public Object getItem(int position) {
        return filmList.get(position);
    }
    
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o estado da posição atual
        Filme filme = filmList.get(position);
        
        // Cria uma instância do layout XML para os objetos correspondentes na View
        LayoutInflater inflater = (LayoutInflater)

        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.film_view, null);

        // Capital
        TextView textFilme = (TextView)view.findViewById(R.id.textFilme);
        textFilme.setText(filme.getNome());        
        
        //icone
        ImageView img = (ImageView)view.findViewById(R.id.imageFilme);
        img.setImageResource(filme.getBanner());
        return view;

    }

}