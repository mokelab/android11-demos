package com.mokelab.demo.android11.bubble

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mokelab.demo.android11.bubble.databinding.BubbleFragmentBinding

class BubbleFragment : Fragment(R.layout.bubble_fragment) {
    var binding: BubbleFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = BubbleFragmentBinding.bind(view)
        this.binding = binding

        binding.buttonCreate.setOnClickListener {
            val name = binding.nameEdit.text.toString()
            val message = binding.messageEdit.text.toString()
            createBubbleNotification(name, message)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    private fun createNotificationChannel() {
        val manager = NotificationManagerCompat.from(requireContext())

        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )

        manager.createNotificationChannel(channel)
    }

    private fun createBubbleNotification(name: String, message: String) {
        if (name.trim().isEmpty()) {
            Snackbar.make(requireView(), "Name must not be empty", Snackbar.LENGTH_SHORT).show()
            return
        }

        val manager = NotificationManagerCompat.from(requireContext())

        val iconBmp = BitmapFactory.decodeResource(resources, R.drawable.moke)
        val personIcon = IconCompat.createWithAdaptiveBitmap(iconBmp)

        val person = Person.Builder().apply {
            setName(name)
            setIcon(personIcon)
        }.build()

        val bubbleMetadata = NotificationCompat.BubbleMetadata.Builder().apply {
            val intent = PendingIntent.getActivity(
                requireContext(),
                1,
                Intent(requireActivity(), BubbleChatActivity::class.java),
                0
            )

            setDesiredHeight(600)
            setIcon(personIcon)
            setIntent(intent)
        }.build()

        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID).apply {
            setLargeIcon(iconBmp)
            setSmallIcon(R.drawable.ic_person_24)
            setStyle(
                NotificationCompat.MessagingStyle(person).addMessage(
                    NotificationCompat.MessagingStyle.Message(
                        message,
                        System.currentTimeMillis(),
                        person
                    )
                )
            )
            setBubbleMetadata(bubbleMetadata)
        }.build()

        manager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        private const val CHANNEL_ID = "bubble"
        private const val NOTIFICATION_ID = 1
    }
}