#LibGDX Kiwi Utilities Library
Kiwi is a small set of utilities inspired by Guava.

Why the fruity name? Well, guava is a fruit after all... that, and I didn't want to create 100th gdx-util out there, to be honest.

Since LibGDX is using its own collections - without implementation of standard Java interfaces, for better and for worse - most Java collection utilities are somewhat useless. That, or they are unavailable for GWT and/or contain a lot of unnecessary classes, which are pretty easy to mix up and import by mistake. Kiwi tries to change that, by having LibGDX-based utilities that could be at least partially useful in pretty much any GDX application.

This library aims to provide what is missing, while **NOT** trying to replace what you can already have. A third common implementation of Optional (Guava, Java 8) would be redundant and unnecessary, for example. That's why most classes try to avoid name collisions with original Java utilities and Guava library - no point in reinventing the wheel or creating confusion.

##Common
The one package that kind of goes against what I've just said. Yeah.
- **Comparables**: not as powerful as Guava, but it will save you some magic numbers in conditions.
- **Nullables**: utilities for objects that might be null. Again, you will probably be better off using optionals, but sometimes we do have to deal with unpleasant external APIs.
- **Strings**: since Scene2D widgets often make use of custom StringBuilder and might actually expect or return CharSequences from time to time, this class provides static utilities (mostly) for CharSequences.
- **TimeUtilities**: time conversion methods. Might be helpful for LibGDX timers and actions.

##Tuples
Ever wanted to refactor and split a method, but you *needed* to return at least two objects at once to make it work? Did you wonder why Java has generic utilities for collections, but misses these simple object containers? Well, you don't have to worry about that using Kiwi, as some simple immutable and mutable pairs and triples implementations are provided.
- **Tuple**: interface of all tuples. Ensures that tuples are serializable and iterable, additionally to providing array-like access by index and utility methods (like contains).
- **DoupleTuple**, **TripleTuple**: these two extend the basic interface, providing additional access methods.
- **Pair**, **Triple**: immutable tuples. Use where possible.
- **MutablePair**, **MutableTriple**: as you can guess, mutable tuples. Use when necessary.
All tuple implementation classes contain static factory method to avoid awkward Java 6 generics syntax.

##GDX
These packages depend on LibGDX and are the core of this library.
- **GdxUtilities**: generic application utilities that were hard to classify. If you're sick of manually glClearing, putting screen width and height into vector or checking if application has the correct type, you will like this class.
- **AbstractApplicationListener**: ApplicationAdapter replacement with render implementation that will clear the screen with black color and call abstract `render(float deltaTime)` method that you're forced to override (which any sensible graphical application should override anyway). Aims to reduce boilerplate code a little and let you focus on the game logic rather than OpenGL calls.

###Assets
- **Disposables**: static utility methods for disposable objects. No more manual iterating over resources or null checks before disposing - this is all handled for you. Note that some objects might throw exceptions when disposing multiple times (or for whatever reason) and some silencing disposing methods are also provided if you really don't care why the exceptions are thrown.
- **StatefulDisposable**: extends Disposable interface with a utility check method isDisposed().
- **Asset**: a utility interface for an enum containing multiple assets. A sample implementation is provided - AbstractInternalAsset (although it is advised to be copied and used by an enum, since they cannot extend).

####Lazy
Hate null check while creating lazy variables? Good, so do I. This call might be much easier to
- **Lazy**: basic class of a lazy variable wrapper. Allows to create a final reference to a wrapped, lazy object that will be initiated (or assigned) on the first get() call thanks to the passed provider object. This class also contains static utility factory methods.
- **DisposableLazy**: extends Lazy; wraps around a disposable object and implements Disposable interface for additional utility.
- **ConcurrentLazy**: extends Lazy; safe to use by multiple threads.
- **DisposableConcurrentLazy**: extends DisposableLazy; safe to use by multiple threads.

###Collections
LibGDX collections utilities. To avoid collisions with Guava and Java API, these classes start with Gdx.
- **GdxArrays**: utilities for LibGDX arrays. Includes array type conversions, common operations, null-safe checks and factory methods.
- **GdxMaps**: utilities for LibGDX maps. Conversions are limited since there is no map interface in LibGDX, but it should be enough for most needs.
- **GdxSets**: utilities for LibGDX sets. Not as powerful as Guava, but you do get union and intersect.
Since disposable and immutable collections provide all static factory methods (TODO), these are not included in GdxCollections utilities.

####Disposable
Utilities for holding multiple assets.
- **DisposableArray**: Array extension implementing Disposable interface.
- **DisposableObjectMap**: ObjectMap extension implementing Disposable interface.
- **DisposableObjectSet**: ObjectSet extension implementing Disposable interface.
All classes come with static factory methods.

####Immutable
Semi-immutable collections extending most common LibGDX containers. Due to original API, full immutability was not possible.
- **ImmutableArray**: Array extension with overshadowed public variables, iterators with blocked remove method and overridden modifying operations. Still mutable when casted to an array and accessing public variables (items, size), but as long as only methods are used, you're safe.
- **ImmutableObjectMap**: ObjectMap extension with overshadowed public variables and overridden modifying operations. Mutable through iterators (which accessed not visible variables and could not have been easily reproduced) or casting to ObjectMap and accessing size variable. As long as you use public methods and standard for-each loops, you're safe.
- **ImmutableObjectSet**: ObjectSet extension with overshadowed public variables and overridden modifying operations. Mutable through iterators (which accessed not visible variables and could not have been easily reproduced) or casting to ObjectSet and accessing size variable. As long as you used public methods and standard for-each loops, you're safe.
All classes come with static factory methods.

### Preferences
- **Preference**: utility interface for common preference operations. Advised to be implemented by an enum to provide static access to application's preferences. Comes with an example implementation - PreferenceWrapper.
- **ApplicationPreferences**: utility container for application's preferences. Manages a map of cached preferences to avoid reading preferences multiple times and ensure that the return Preferences object for the given path is always the same and in sync.

### Scene2D
- **Actors**: common utility methods for Scene2D actors.
- **Alignment**: wraps around Align class to provide human-(instantly-)readable alignment checking methods.
- **Padding**: utility container for paddings and spacings. Makes it easier to keep static padding settings.
- **InterfaceSkin**: utility container that provides static access to UI skin.

#### Ranges
- **ColorRange**: utility for simple color transitions.
- **FloatRange**: utility for simple float transitions.