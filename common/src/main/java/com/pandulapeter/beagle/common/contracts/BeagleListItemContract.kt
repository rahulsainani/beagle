package com.pandulapeter.beagle.common.contracts

/**
 * Models used for representing items in the ItemListModule, SingleSelectionListModule or MultipleSelectionListModule must implement this interface.
 */
interface BeagleListItemContract {

    /**
     * The text that should appear on the UI.
     */
    val title: CharSequence

    /**
     * A unique identifier for the list item that is used in callbacks.
     *
     * It defaults to the title as this is suitable for most use cases
     */
    val id: String get() = title.toString()

    /**
     * To simplify diff calculation, derived classes are encouraged to be data classes.
     */
    override fun equals(other: Any?): Boolean

    /**
     * To simplify diff calculation, derived classes are encouraged to be data classes.
     */
    override fun hashCode(): Int
}