# Kotlin-Post-App
Android application task<br />

Create an application which consists of these screens:<br />
    1) Main screen:<br />
Screen shows list of posts (titles only), has pull to refresh, displays refreshing state if posts are being loaded. On item click, Single post details screen should be opened.<br />

    2) Single post details screen<br />
Screen should have User’s name and rounded photo, Post title and body;<br />

Data should be stored in database, initially loaded from database and refreshed from API. <br />
If error occurs (for example no network connection) - error dialog is shown with general error message, cancel and retry buttons.<br />
Cover code with Unit tests<br />

APIs:<br />
    • Posts: https://jsonplaceholder.typicode.com/posts<br />
    • Post details: https://jsonplaceholder.typicode.com/posts/{post_id}<br />
    • User details: https://jsonplaceholder.typicode.com/users/{user_id} <br />
    • User photo  https://source.unsplash.com/collection/542909/?sig={user_id}<br />

Use: <br />
    • Kotlin <br />
    • RxKotlin or Coroutines <br />
    • MVVM/MVVMi pattern <br />
    • Navigation component <br />
    • Android Architecture Components <br />
    • Dependency Injection (Dagger2/Hilt/...) <br />
    • Room or Realm <br />


![alt text](https://i.imgur.com/jAwfCA0.png)
