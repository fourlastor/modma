package io.github.fourlastor.modma.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.LocalSaveableStateRegistry
import androidx.compose.runtime.saveable.SaveableStateRegistry
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.Router
import com.arkivanov.decompose.lifecycle.Lifecycle
import com.arkivanov.decompose.lifecycle.LifecycleRegistry
import com.arkivanov.decompose.lifecycle.destroy
import com.arkivanov.decompose.lifecycle.resume
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.ParcelableContainer
import com.arkivanov.decompose.statekeeper.StateKeeper
import com.arkivanov.decompose.statekeeper.StateKeeperDispatcher
import kotlin.reflect.KClass

@Composable
private fun rememberLifecycle(): Lifecycle {
    val lifecycle = remember { LifecycleRegistry() }

    DisposableEffect(Unit) {
        lifecycle.resume()
        onDispose { lifecycle.destroy() }
    }

    return lifecycle
}

private const val KEY_STATE = "STATE"

@Composable
private fun rememberStateKeeper(): StateKeeper {
    val saveableStateRegistry: SaveableStateRegistry? = LocalSaveableStateRegistry.current

    val dispatcher =
        remember {
            StateKeeperDispatcher(saveableStateRegistry?.consumeRestored(KEY_STATE) as ParcelableContainer?)
        }

    if (saveableStateRegistry != null) {
        DisposableEffect(Unit) {
            val entry = saveableStateRegistry.registerProvider(KEY_STATE, dispatcher::save)
            onDispose { entry.unregister() }
        }
    }

    return dispatcher
}

@Composable
private fun rememberComponentContext(): ComponentContext {
    val lifecycle = rememberLifecycle()
    val stateKeeper = rememberStateKeeper()

    return remember {
        DefaultComponentContext(
            lifecycle = lifecycle,
            stateKeeper = stateKeeper
        )
    }
}

@Composable
fun <C : Parcelable> rememberRouter(
    initialConfiguration: () -> C,
    configurationClass: KClass<out C>
): Router<C, Any> {
    val context = rememberComponentContext()

    return remember {
        context.router(
            initialConfiguration = initialConfiguration,
            configurationClass = configurationClass
        ) { configuration, _ -> configuration }
    }
}

@Composable
inline fun <reified C : Parcelable> rememberRouter(
    noinline initialConfiguration: () -> C
): Router<C, Any> =
    rememberRouter(
        initialConfiguration = initialConfiguration,
        configurationClass = C::class
    )
