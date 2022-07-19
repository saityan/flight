package saityan.misc.flight.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieDrawable
import saityan.misc.flight.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.background.repeatCount = LottieDrawable.INFINITE
        binding.lottie.repeatCount = LottieDrawable.INFINITE
        val width = requireContext().resources.displayMetrics.widthPixels.toFloat()
        val height = requireContext().resources.displayMetrics.heightPixels.toFloat()
        val offset = 300
        initAnimationListener(width, height, offset)
    }

    private fun initAnimationListener(width: Float, height: Float, offset: Int) {
        binding.lottie.setOnClickListener {
            it.animate()
                .setInterpolator(AccelerateInterpolator(0.3F))
                .setDuration(2000)
                .translationX(width / 4)
                .withEndAction {
                    it.animate()
                        .setInterpolator(DecelerateInterpolator(0.75F))
                        .translationY(height / 2)
                        .translationX(width/4 * 3)
                        .withEndAction {
                            it.animate()
                                .setInterpolator(DecelerateInterpolator(1.5F))
                                .setDuration(1200)
                                .translationX(-width / 4 + offset)
                                .translationY(height - offset)
                                .withEndAction {
                                    it.animate()
                                        .setInterpolator(AccelerateInterpolator(3F))
                                        .translationX(width - offset)
                                }
                        }
                }
        }
    }
}
