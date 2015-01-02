//package com.example.snowball;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//
//public class MySearchViewProvider extends SearchProvider{
//	private Context context;  
//    private LayoutInflater inflater;  
//    private View view;  
////    private ImageView button;  
//    public MySearchViewProvider(Context context) {  
//        super(context);  
//        // TODO Auto-generated constructor stub  
//        this.context = context;  
//        inflater = LayoutInflater.from(context);  
//        view = inflater.inflate(R.layout.actionbar_search_nav, null);  
//    }  
//  
//      
//    @Override  
//    public View onCreateActionView() {  
//        // TODO Auto-generated method stub  
//        button = (ImageView) view.findViewById(R.id.button);  
//        button.setOnClickListener(new View.OnClickListener() {  
//              
//            @Override  
//            public void onClick(View v) {  
//                // TODO Auto-generated method stub  
//                Toast.makeText(context, " «Œ“£¨√ª¥Ì", Toast.LENGTH_SHORT).show();  
//            }  
//        });  
//        return view;  
//    } 
//}