package com.example.nbaplayerswiki

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayerswiki.model.Player
import com.example.nbaplayerswiki.ui.home.BalldontlieApiStatus
import com.example.nbaplayerswiki.ui.home.PlayerListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Player>?) {
    val adapter = recyclerView.adapter as PlayerListAdapter
    adapter.submitList(data)
}

@BindingAdapter("firstName")
fun bindFirstName(textView: TextView, firstName: String?) {
    if (firstName.isNullOrEmpty()) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = firstName
    }

}

@BindingAdapter("lastName")
fun bindLastName(textView: TextView, lastName: String?) {
    if (lastName.isNullOrEmpty()) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = lastName
    }
}

@BindingAdapter("position")
fun bindPosition(textView: TextView, position: String?) {
    if (position.isNullOrEmpty()) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = position
    }
}

@BindingAdapter("heightFeet")
fun bindHeightFeet(textView: TextView, heightFeet: Int?) {
    if (heightFeet == null) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = heightFeet.toString()
    }
}

@BindingAdapter("heightInches")
fun bindHeightInches(textView: TextView, heightInches: Int?) {
    if (heightInches == null) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = heightInches.toString()
    }
}

@BindingAdapter("weightPounds")
fun bindWeightPounds(textView: TextView, weightPounds: Int?) {
    if (weightPounds == null) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = weightPounds.toString()
    }
}

//@BindingAdapter("teamId")
//fun bindTeamId(textView: TextView, teamId: Int?) {
//    textView.text = teamId.toString()
//}
//
//@BindingAdapter("teamAbbreviation")
//fun bindTeamAbbreviation(textView: TextView, teamAbbreviation: String?) {
//    textView.text = teamAbbreviation
//}

@BindingAdapter("teamCity")
fun bindTeamCity(textView: TextView, teamCity: String?) {
    if (teamCity.isNullOrEmpty()) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = teamCity
    }
}

@BindingAdapter("teamConference")
fun bindTeamConference(textView: TextView, teamConference: String?) {
    if (teamConference.isNullOrEmpty()) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = teamConference
    }
}

@BindingAdapter("teamDivision")
fun bindTeamDivision(textView: TextView, teamDivision: String?) {
    if (teamDivision.isNullOrEmpty()) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = teamDivision
    }
}

@BindingAdapter("teamFullName")
fun bindTeamFullName(textView: TextView, teamFullName: String?) {
    if (teamFullName.isNullOrEmpty()) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = teamFullName
    }
}

//@BindingAdapter("teamName")
//fun bindTeamName(textView: TextView, teamName: String?) {
//    textView.text = teamName
//}

@BindingAdapter("season")
fun bindSeasonYear(textView: TextView, seasonYear: Int?) {
    if (seasonYear == null) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = seasonYear.toString()
    }
}

@BindingAdapter("pts")
fun bindPoints(textView: TextView, points: Double?) {
    if (points == null) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = points.toString()
    }
}

@BindingAdapter("gamesPlayed")
fun bindGamesPlayed(textView: TextView, gamesPlayed: Int?) {
    if (gamesPlayed == null) {
        textView.text = textView.context.getString(R.string.unknown)
    } else {
        textView.text = gamesPlayed.toString()
    }
}

@BindingAdapter("appVersion")
fun bindAppVersion(textView: TextView, name: String?) {
    textView.let {
        textView.text = name
    }
}

/**
 * This binding adapter displays the [BalldontlieApiStatus] of the network request in an image view.
 * 1) When the request is loading, it displays a loading_animation.
 * 2) If the request has an error, it displays a broken image to reflect the connection error.
 * 3) When the request is finished, it hides the image view.
 */
@BindingAdapter("balldontlieApiStatus")
fun bindStatus(statusImageView: ImageView, status: BalldontlieApiStatus) {
    when (status) {
        BalldontlieApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        BalldontlieApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        BalldontlieApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}