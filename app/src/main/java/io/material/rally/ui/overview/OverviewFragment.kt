package io.material.rally.ui.overview

import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.material.rally.R
import io.material.rally.ui.overview.adapter.Account
import io.material.rally.ui.overview.adapter.AccountOverviewAdapter
import io.material.rally.ui.overview.adapter.Bill
import io.material.rally.ui.overview.adapter.BillAdapter
import kotlinx.android.synthetic.main.fragment_overview.content
import kotlinx.android.synthetic.main.layout_account_overview.rv_account_overview
import kotlinx.android.synthetic.main.layout_bill_overview.rv_bill_overview

class OverviewFragment : Fragment() {

  private val accountAdapter by lazy { AccountOverviewAdapter() }
  private val billAdapter by lazy { BillAdapter() }

  private val decoration by lazy {
    DividerItemDecoration(requireContext(), RecyclerView.VERTICAL).apply {
      val divider = ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!
      val margin = resources.getDimensionPixelSize(R.dimen.spacing_medium)
      setDrawable(InsetDrawable(divider, margin, 0, margin, 0))
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_overview, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    setUpAccountRecyclerView()
    setUpBillRecyclerView()

    if (savedInstanceState == null) runEnterAnimation()
  }

  private fun setUpAccountRecyclerView() {
    rv_account_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(decoration)
      adapter = accountAdapter
    }
    accountAdapter.submitList(listOf(Account(""), Account(""), Account("")))

  }

  private fun setUpBillRecyclerView() {
    rv_bill_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      addItemDecoration(decoration)
      adapter = billAdapter
    }
    billAdapter.submitList(listOf(Bill(""), Bill(""), Bill("")))
  }

  private fun setUpBudgetRecyclerView() {
    rv_account_overview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      setHasFixedSize(true)
      adapter = null
    }
  }

  private fun runEnterAnimation() {
    content.post {
      var duration = 300L
      for (i in 0 until content.childCount) {
        duration += 100
        val child = content.getChildAt(i)
        child.translationY += 400
        child.alpha = 0f
        child.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .start()
      }
    }
  }

}
