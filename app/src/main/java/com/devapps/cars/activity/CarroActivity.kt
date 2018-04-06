package com.devapps.cars.activity

import android.os.Bundle
import com.devapps.cars.R
import com.devapps.cars.domain.Carro
import com.devapps.cars.extensions.loadUrl
import com.devapps.cars.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro_contents.*
import kotlinx.android.synthetic.main.activity_carro.*

class CarroActivity : BaseActivity() {
    val carro: Carro by lazy { intent.getParcelableExtra<Carro>("carro") }

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_carro)
        // Seta o nome do carro como título da Toolbar
        setupToolbar(R.id.toolbar, carro.nome, true)
        // Atualiza os dados do carro na tela
        initViews()
    }

    fun initViews() {
        // Variáveis  geradas automaticamente pelo Kotlin Extensions (veja import)
        tDesc.text = carro.desc
        appBarImg.loadUrl(carro.urlFoto)
    }
}
