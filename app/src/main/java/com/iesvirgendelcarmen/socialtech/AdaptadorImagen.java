package com.iesvirgendelcarmen.socialtech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

    public class AdaptadorImagen extends BaseAdapter {
        Context context;
        int images[];
        LayoutInflater inflter;

        public AdaptadorImagen(Context applicationContext, int[] flags) {
            this.context = applicationContext;
            this.images = flags;
            inflter = (LayoutInflater.from(applicationContext));
        }

        public Context getContext() {
            return context;
        }



        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.spinner_imagen, null);
            ImageView icon = (ImageView) view.findViewById(R.id.imageView);
            icon.setImageResource(images[i]);
            return view;
        }
    }







