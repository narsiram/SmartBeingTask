package com.example.narsiramjindal.smartbeingtask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.narsiramjindal.smartbeingtask.R;
import com.example.narsiramjindal.smartbeingtask.model.UsersModel;

import java.util.List;

public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.Holder>
{
    private Context context;
    private LayoutInflater inflater = null;
    private List<UsersModel> userList = null ;

    public UserDetailAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public Holder onCreateViewHolder(ViewGroup parent, int viewtype)
    {
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.user_detail_card, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position)
    {
        UsersModel data = userList.get(position) ;

        holder.setTexts(data.getLogin() , data.getId() , data.getAvatarUrl() );

       //pagination code
        /*if(currentPage < totalPage)
        {
            if(position == products.size()-1)
            {
                currentPage = currentPage + 1;

                onMoreDataLoaded.getMoreCardForPage(currentPage);
            }
            else
                holder.pBarForReloadData.setVisibility(View.GONE);
        }
        else
            holder.pBarForReloadData.setVisibility(View.GONE);*/
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0 ;
    }

    public void addDataToAdapter(List<UsersModel> userList)
    {
        this.userList = userList ;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder
    {
        private TextView userName , loginId , avatarUrl  ;

        public Holder(View itemView)
        {
            super(itemView);

            userName = itemView.findViewById(R.id.txt_name);
            loginId = itemView.findViewById(R.id.txt_login_id);
            avatarUrl =itemView.findViewById(R.id.txt_url);

        }

        public void setTexts(String txtName , int txtLoginId ,String url)
        {
            userName.setText(txtName);
            loginId.setText(txtLoginId + "");
            avatarUrl.setText(url);
        }
    }

    //pagination interface
/*
    public interface OnMoreDataLoaded
    {
        void getMoreCardForPage(int page);
    }*/
}