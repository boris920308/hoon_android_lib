package exam.hoon.hoon_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FavoriteFragment : Fragment() {

    private lateinit var btnGoSetting : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGoSetting = view.findViewById(R.id.btn_go_setting)
        btnGoSetting.setOnClickListener {
            findNavController().navigate(R.id.action_favoriteFragment_to_settingFragment)
        }

    }
}