package uz.infinityandro.taskguidebook.presentation.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import uz.infinityandro.taskguidebook.data.User
import uz.infinityandro.taskguidebook.databinding.ItemBookBinding

class UserDataAdapter(private val list: List<User>, var listener:(model: User)->Unit):RecyclerView.Adapter<UserDataAdapter.VH>()  {
    inner class VH(val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                listener(list[bindingAdapterPosition])
            }
        }

        fun bind() = with(binding){
            val dataItem=list[bindingAdapterPosition]
            name.text=dataItem.name
            city.text= dataItem.venue.toString()
            start.text="Started: ${dataItem.startDate}"
            endDate.text="End: ${dataItem.endDate}"

            Glide.with(root.context).load(dataItem.icon).centerCrop().listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress.visibility= View.GONE
                    return false
                }

            }).into(imageLogo)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int =list.size
}