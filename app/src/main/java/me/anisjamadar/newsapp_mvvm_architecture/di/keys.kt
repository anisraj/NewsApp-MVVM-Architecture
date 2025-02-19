package me.anisjamadar.newsapp_mvvm_architecture.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)