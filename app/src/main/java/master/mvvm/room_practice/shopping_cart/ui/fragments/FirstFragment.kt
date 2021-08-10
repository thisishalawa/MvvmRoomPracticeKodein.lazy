package master.mvvm.room_practice.shopping_cart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_first.*
import master.mvvm.room_practice.databinding.FragmentFirstBinding
import master.mvvm.room_practice.shopping_cart.adapters.ShoppingItemAdapter
import master.mvvm.room_practice.shopping_cart.data.ShoppingItem
import master.mvvm.room_practice.shopping_cart.dialog.AddDialogListener
import master.mvvm.room_practice.shopping_cart.dialog.AddShoppingItemDialog
import master.mvvm.room_practice.shopping_cart.ui.ShoppingViewModel
import master.mvvm.room_practice.shopping_cart.ui.ShoppingViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class FirstFragment : Fragment(), KodeinAware {
    /*   kodein
        import org.kodein.di.android.kodein
     *   instance with replaced in Kodein Application Dependency
     *   val database = ShoppingDatabase(this)
     *   val repository = ShoppingRepository(database)
     *   val factory = ShoppingViewModelFactory(repository)
     *    NO !!
     *    private val factory: ShoppingViewModelFactory by instance()
     *
     * */
    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    // binding
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    // viewModelAdapter
    private var adapter: ShoppingItemAdapter? = null
    lateinit var viewModel: ShoppingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        adapter = ShoppingItemAdapter(listOf(), viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(activity)
        rvShoppingItems.adapter = adapter
        getAllShoppingItems()

        fabAdd.setOnClickListener {
            showAddStuffDialog()
        }
    }

    private fun showAddStuffDialog() {
        AddShoppingItemDialog(
            requireActivity(),
            object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
    }

    private fun getAllShoppingItems() {
        /* return LiveData of List Shopping Items
        * have an observer ..  */
        viewModel.getAllShoppingItems().observe(requireActivity(), Observer {
            adapter!!.items = it /*list of Shopping Item this item will return*/
            adapter!!.notifyDataSetChanged()
        })
    }

}
