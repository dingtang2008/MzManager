package com.elvis.mzmanager.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.elvis.mzmanager.R;
import com.elvis.mzmanager.entity.CardEntity.RowsBean;
import com.idogfooding.backbone.ui.rv.RVAdapter;


public class CardListAdapter extends RVAdapter<RowsBean, BaseViewHolder> {

    public CardListAdapter() {
        super(R.layout.item_card);
    }

    @Override
    protected void convert(BaseViewHolder holder, RowsBean item) {


        holder.setText(R.id.iccid, item.getIccidMark());
        holder.setText(R.id.carrier, item.getCategoryStr());
        holder.setText(R.id.totalFlow, item.getTotalFlowBig() + "GB");
        holder.setText(R.id.usedFlow, item.getUsedFlowBig() + "GB");
        holder.setText(R.id.leftFlow, item.getRemainingFlowBig() + "GB");

        if (item.getCardLifeEntity() != null) {
            String times[] = item.getCardLifeEntity().getExpireDate().split(" ");

            holder.setText(R.id.expiredTime, times[0]);
        }
        holder.setText(R.id.agentName, item.getAgentName() );
    }
}
