package org.example;

// This Interface defines one method - matches().
// We can define classes that implement this interface 
// and override the matches() method to match objects
// based on speficic fields.
// 
// Note that it is not necessary to preceed the name of 
// an interface with "I" (as in IFilter), however, it
// is often used as a naming convention to help code readability.

// Every filter class that we define must implement the IFilter interface.
// The matches() methed must then be implemented in each filter.
// We can then use the interface type IFilter as the reference type
// for the filterBy( IFilter filter ) method
// Using this approach, we can turn any filter object into an IFilter
// type by coding it to implement the IFilter Interface.
// (Think about what type you could use if if we did not have an interface.)
//

/**
 * IFilter interface that defines one method - matches()
 * Note the type of the parameter is "Object" so that it can
 * deal with any object type.
 *
 * @returns true if this object 'matches' other object, otherwise false
 */
public interface IFilter {
    public boolean matches(Object other);
}
