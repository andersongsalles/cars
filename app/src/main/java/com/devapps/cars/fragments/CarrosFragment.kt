package com.devapps.cars.fragments

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devapps.cars.R
import com.devapps.cars.activity.CarroActivity
import com.devapps.cars.adapter.CarroAdapter
import com.devapps.cars.domain.Carro
import com.devapps.cars.domain.CarroService
import com.devapps.cars.domain.TipoCarro

import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CarrosFragment : BaseFragment() {
    private var tipo = TipoCarro.classicos
    private var carros = listOf<Carro>()
    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        tipo = arguments?.getSerializable("tipo") as TipoCarro
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, icicle: Bundle?): View? {
        // Retorna a view /res/layout/fragment_carros.xml
        val view = inflater?.inflate(R.layout.fragment_carros, container, false)
        return view
    }

    override fun onViewCreated(view: View, icicle: Bundle?) {
        super.onViewCreated(view, icicle)
        // Views
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskCarros()
    }

    fun taskCarros() {
        // Abre uma thread
        doAsync {
            // Busca os carros
            carros = CarroService.getCarros(tipo)
            // Atualiza a lista na UI Thread
            uiThread {
                recyclerView.adapter = CarroAdapter(carros) { onClickCarro(it) }
            }
        }
    }

    fun onClickCarro(carro: Carro) {
        activity?.startActivity<CarroActivity>("carro" to carro)
    }
}
