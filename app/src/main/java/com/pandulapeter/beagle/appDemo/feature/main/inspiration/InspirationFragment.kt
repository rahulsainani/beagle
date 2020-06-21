package com.pandulapeter.beagle.appDemo.feature.main.inspiration

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import com.pandulapeter.beagle.appDemo.R
import com.pandulapeter.beagle.appDemo.data.CaseStudy
import com.pandulapeter.beagle.appDemo.feature.main.inspiration.authentication.AuthenticationFragment
import com.pandulapeter.beagle.appDemo.feature.main.inspiration.basicSetup.BasicSetupFragment
import com.pandulapeter.beagle.appDemo.feature.main.inspiration.featureToggles.FeatureTogglesFragment
import com.pandulapeter.beagle.appDemo.feature.main.inspiration.list.InspirationAdapter
import com.pandulapeter.beagle.appDemo.feature.main.inspiration.list.InspirationListItem
import com.pandulapeter.beagle.appDemo.feature.shared.ListFragment
import com.pandulapeter.beagle.appDemo.utils.TransitionType
import com.pandulapeter.beagle.appDemo.utils.handleReplace
import com.pandulapeter.beagle.appDemo.utils.showSnackbar
import com.pandulapeter.beagle.modules.TextModule
import org.koin.androidx.viewmodel.ext.android.viewModel

class InspirationFragment : ListFragment<InspirationViewModel, InspirationListItem>(R.string.inspiration_title) {

    override val viewModel by viewModel<InspirationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postponeEnterTransition()
        super.onViewCreated(view, savedInstanceState)
        refreshBeagle()
    }

    override fun createAdapter() = InspirationAdapter(viewModel.viewModelScope, ::onCaseStudySelected)

    override fun getBeagleModules() = mutableListOf(TextModule(text = getString(R.string.inspiration_beagle_text)))

    private fun onCaseStudySelected(caseStudy: CaseStudy, view: View) = when (caseStudy) {
        CaseStudy.BASIC_SETUP -> navigateTo(BasicSetupFragment.Companion::newInstance, view)
        CaseStudy.AUTHENTICATION -> navigateTo(AuthenticationFragment.Companion::newInstance, view)
        CaseStudy.FEATURE_TOGGLES -> navigateTo(FeatureTogglesFragment.Companion::newInstance, view)
        else -> binding.root.showSnackbar(caseStudy.title)
    }

    private inline fun <reified T : InspirationDetailFragment<*, *>> navigateTo(crossinline newInstance: () -> T, sharedElement: View) = parentFragmentManager.handleReplace(
        addToBackStack = true,
        sharedElements = listOf(sharedElement),
        transitionType = TransitionType.MODAL,
        newInstance = newInstance
    )

    companion object {
        fun newInstance() = InspirationFragment()
    }
}