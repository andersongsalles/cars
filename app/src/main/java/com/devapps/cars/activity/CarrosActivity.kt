package com.devapps.cars.activity

import android.os.Bundle
import com.devapps.cars.R

import com.devapps.cars.activity.BaseActivity
import com.devapps.cars.domain.TipoCarro
import com.devapps.cars.extensions.addFragment
import com.devapps.cars.extensions.setupToolbar
import com.devapps.cars.fragments.CarrosFragment

class CarrosActivity : BaseActivity() {
    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_carros)
        // Argumentos: tipo do carro
        val tipo = intent.getSerializableExtra("tipo") as TipoCarro
        val title = getString(tipo.string)
        // Toolbar: configura o título e o "up navigation"
        setupToolbar(R.id.toolbar, title, true)
        if (icicle == null) {
            // Adiciona o fragment no layout de marcação
            // Dentre os argumentos que foram passados para a activity, está o tipo do carro.
            addFragment(R.id.container, CarrosFragment())
        }
    }
}
