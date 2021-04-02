package com.example.sqlsozlukuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KelimelerAdapter extends  RecyclerView.Adapter<KelimelerAdapter.CardTasarımTutucu> {
    private Context mContext;
    private List<Kelimeler> kelimelerListe;

    public KelimelerAdapter(Context mContext, List<Kelimeler> kelimelerListe) {
        this.mContext = mContext;
        this.kelimelerListe = kelimelerListe;
    }

    @NonNull
    @Override
    public CardTasarımTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardTasarımTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarımTutucu holder, int position) {

        Kelimeler kelime = kelimelerListe.get(position);

        holder.textViewIngilizce.setText(kelime.getIngilizce());
        holder.textViewTurkce.setText(kelime.getTurkce());

        holder.kelime_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,DetayActivity.class);
                intent.putExtra("nesne",kelime);
                mContext.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return kelimelerListe.size();
    }

    public class CardTasarımTutucu extends RecyclerView.ViewHolder{

        private TextView textViewIngilizce;
        private TextView textViewTurkce;
        private CardView kelime_card;

        public CardTasarımTutucu(@NonNull View itemView) {
            super(itemView);

            textViewIngilizce = itemView.findViewById(R.id.textViewIngilizce);
            textViewTurkce = itemView.findViewById(R.id.textViewTurkce);
            kelime_card = itemView.findViewById(R.id.kelime_card);
        }
    }
}
