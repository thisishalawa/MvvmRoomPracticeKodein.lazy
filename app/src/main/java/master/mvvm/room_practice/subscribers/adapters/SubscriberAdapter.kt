package master.mvvm.room_practice.subscribers.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import master.mvvm.room_practice.R
import master.mvvm.room_practice.databinding.SubscriberItemBinding
import master.mvvm.room_practice.subscribers.data.Subscriber

class SubscriberAdapter(private val clickListener: (Subscriber) -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {
    private val subscribersList = ArrayList<Subscriber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SubscriberItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.subscriber_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList[position], clickListener)
    }

    fun setList(subscribers: List<Subscriber>) {
        subscribersList.clear()
        subscribersList.addAll(subscribers)

    }

}

class MyViewHolder(private val mBinding: SubscriberItemBinding) :
    RecyclerView.ViewHolder(mBinding.root) {

    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
        mBinding.sub = subscriber
        mBinding.listItemLayout.setOnClickListener {
            clickListener(subscriber)
        }
    }
}