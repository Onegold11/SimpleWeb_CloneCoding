## SimpleWeb_CloneCoding
간단한 웹 브라우저 앱

## 사용한 요소
- UI
  - ImageButton
  - EditText 속성
    - selectAllOnFocus
    - importantForAutofill
    - imeOptions
  - SwipeRefreshLayout
  - ContentLoadingProgressBar
- Web
  - WebView
  
## ImageButton
[공식 문서](https://developer.android.com/reference/android/widget/ImageButton)

TextView를 상속하는 Button과 달리 ImageView를 상속받는 클래스
- background를 "?attr/selectableItemBackground"로 지정하면 이미지만 표시할 수 있다.

## selectAllOnFocus
EditText에서 선택되었을 때 모든 텍스트가 선택되는 옵션

## importantForAutofill
[개발자 문서](https://developer.android.com/guide/topics/text/autofill-optimize?hl=ko)

EditText에서 자동 완성 기능을 설정하는 옵션

## imeOptions
EditText에서 입력이 완료됐을 때 할 동작을 설정하는 옵션
- EditText에서 setOnEditorActionListener를 설정하면 동작에 따른 이벤트를 설정할 수 있다.
```kotlin
this.addressEdit.setOnEditorActionListener {v, actionId, event ->
  if (actionId == EditorInfo.IME_ACTION_DONE {
    ...
  }
```

## SwipeRefreshLayout
[공식 문서](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout?hl=ko)

새로고침 기능을 추가할 때 사용하는 레이아웃
```xml
dependencies {
	implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
}
```
setOnRefreshListener를 등록해서 새로고침할 요소를 지정할 수 있다.
```kotlin
this.refreshLayout.setOnRefreshListener {

            this.webView.reload()
        }
```
## ContentLoadingProgressBar
콘텐츠의 로딩을 표시하는 바
```kotlin
progressBar.show()
progressBar.hide()
progressBar.progress = newProgress
```
- show와 hide를 사용해 표시/해제
- progress 에 값을 입력하면 진행바를 

## WebView
[공식 문서](https://developer.android.com/guide/webapps/webview?hl=ko)

웹 브라우저 기능을 담당하는 뷰
```kotlin
// Url 로딩
webView.loadUrl(HOME_URL)
// 뒤로가기
webView.goBack()
// 앞으로가기
webView.goForward()
// 새로고침
webView.reload()
// 뒤로가기 가능 여부
webView.canGoBack()
// 앞으로가기 가능 여부
webView.canGoForward()
```

- WebViewClient: 페이지의 시작/종료 등 알림과 리퀘스트에 대한 처리를 설정하는 클래스
- WebChromeClient: 자바스크립트 다이얼로그, 파비콘, 제목, 진행바 등을 이용할 때 설정하는 클래스
- settings.javaScriptEnabled: 자바스크립트 사용 여부

