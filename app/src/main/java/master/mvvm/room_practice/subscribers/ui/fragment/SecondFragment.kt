package master.mvvm.room_practice.subscribers.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import master.mvvm.room_practice.databinding.FragmentSecondBinding
import master.mvvm.room_practice.subscribers.SubscriberViewModelFactory
import master.mvvm.room_practice.subscribers.adapters.SubscriberAdapter
import master.mvvm.room_practice.subscribers.data.Subscriber
import master.mvvm.room_practice.subscribers.data.db.SubscriberDatabase
import master.mvvm.room_practice.subscribers.repository.SubscriberRepository
import master.mvvm.room_practice.subscribers.ui.SubscriberViewModel

class SecondFragment : Fragment() {

    // binding
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    // viewModel
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var subscriberAdapter: SubscriberAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /*    Dao & Repository & factory instance
        *     Instance object in Activity class -> very Bad Practice
        *     val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        *     val repository = SubscriberRepository(dao)
        *     val factory = SubscriberViewModelFactory(repository)
        *     So ! - Kodein Dependency Injection
        * */


        val dao = SubscriberDatabase.getInstance(requireContext()).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)


        /* ViewModel Instance
        * Assign ViewModel Object to DataBinding
        * LifeData With DataBinding -> must Provide LifeCycle Owner
        * */
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.viewModel = subscriberViewModel
        binding.lifecycleOwner = this


        initRecyclerView()
        subscriberViewModel.message.observe(viewLifecycleOwner, Observer { it ->
            if (it == null) return@Observer

            it.getContentIfNotHandled()?.let {
                Log.d(TAG, "data :  $it")
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this@SecondFragment.context)
        subscriberAdapter =
            SubscriberAdapter { selectedItem: Subscriber -> listItemClicked(selectedItem) }
        binding.recyclerView.adapter = subscriberAdapter
        displaySubscribersList()
    }

    private fun listItemClicked(subscriber: Subscriber) {
       // Toast.makeText(context, "selected name is ${subscriber.name}", Toast.LENGTH_LONG).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displaySubscribersList() {
        /* observer Data
        *
        * */
        subscriberViewModel.subscribers.observe(requireActivity(), {
            Log.i(TAG, it.toString())
            subscriberAdapter.setList(it)
            subscriberAdapter.notifyDataSetChanged()
        })
    }

    companion object {
        private const val TAG = "SecondFragmentDEBUG!"
    }

}
