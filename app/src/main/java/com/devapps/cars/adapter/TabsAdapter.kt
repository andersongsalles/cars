package com.devapps.cars.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.devapps.cars.domain.TipoCarro
import com.devapps.cars.fragments.CarrosFragment


class TabsAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    // Qtde de Tabs
    override fun getCount(): Int = 3

    // Retorna o tipo pela posição
    fun getTipoCarro(position: Int) = when (position) {
        0 -> TipoCarro.classicos
        1 -> TipoCarro.esportivos
        else -> TipoCarro.luxo
    }

    // Título da Tab
    override fun getPageTitle(position: Int): CharSequence {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)
    }

    // Fragment que vai mostrar a lista de carros
    override fun getItem(position: Int): Fragment {
        val tipo = getTipoCarro(position)
        val f: Fragment = CarrosFragment()
        val arguments = Bundle()
        arguments.putSerializable("tipo", tipo)
        f.arguments = arguments
        return f
    }
}
