# melipayamak-Java EE for Android

<div dir='rtl'>

### معرفی وب سرویس ملی پیامک
ملی پیامک یک وب سرویس کامل برای ارسال و دریافت پیامک و پیامک صوتی و مدیریت کامل خدمات دیگر است که براحتی میتوانید از آن استفاده کنید.

### نصب
<hr>
<p>قبل از نصب نیاز به ثبت نام در سایت ملی پیامک دارید.</p>

[**ثبت نام به همراه 500 پیامک رایگان اولیه تست وبسرویس**](http://www.melipayamak.com/)

</div>

<div dir='rtl'>
  
#### نحوه استفاده

نمونه کد برای ارسال پیامک

</div>


```js
const string username = "username";
const string password = "password";
const string from = "5000...";
const string to = "09123456789";
const string text = "تست وب سرویس ملی پیامک";
const bool isFlash = false;
SendSoapClient soapClient = new SendSoapClient();
soapClient.SendSimpleSMS2(username, password, to, from, text, isFlash);
//یا برای ارسال به مجموعه ای از مخاطبین
soapClient.SendSimpleSMS(username, password, new string[] { to }, from, text, isFlash);
```

<div dir='rtl'>

از آنجا که وب سرویس ملی پیامک تنها محدود به ارسال پیامک نیست شما از طریق زیر میتوانید به وب سرویس ها دسترسی کامل داشته باشید:
</div>

```js
// وب سرویس پیامک
RestClient restClient = new RestClient(username, password);
SendSoapClient soapClient = new SendSoapClient();
// وب سرویس تیکت پشتیبانی
TicketsSoapClient ticketSoapClient = new TicketsSoapClient();
// وب سرویس برای مدیریت کامل  ارسال انبوه پیامک
ActionsSoapClient actionSoapClient = new ActionsSoapClient();
//وب سرویس کاربران
UsersSoapClient usersSoapClient = new UsersSoapClient();
//وب سرویس دفترچه تلفن
ContactsSoapClient contactSoapClient = new ContactsSoapClient();
//وب سرویس زمان بندی
ScheduleSoapClient scheduleSoapClient = new ScheduleSoapClient();
//وب سرویس پیام صوتی
VoiceSoapClient voiceSoapClient = new VoiceSoapClient();
//وب سرویس دریافت
ReceiveSoapClient receiveSoapClient = new ReceiveSoapClient();
```

<div dir='rtl'>
  
#### تفاوت های وب سرویس پیامک rest و soap

از آنجا که ملی پیامک وب سرویس کاملی رو در اختیار توسعه دهندگان میگزارد برای راحتی کار با وب سرویس پیامک علاوه بر وب سرویس اصلی soap وب سرویس rest رو هم در اختیار توسعه دهندگان گزاشته شده تا راحتتر بتوانند با وب سرویس کار کنند. تفاوت اصلی این دو در تعداد متد هاییست که میتوانید با آن کار کنید. برای کار های پایه میتوان از وب سرویس rest استفاده کرد برای دسترسی بیشتر و استفاده پیشرفته تر نیز باید از وب سرویس باید از وب سرویس soap استفاده کرد. جهت مطالعه بیشتر وب سرویس ها به قسمت وب سرویس پنل خود مراجعه کنید.

<hr/>


###  اطلاعات بیشتر

برای مطالعه بیشتر و دریافت راهنمای وب سرویس ها و آشنایی با پارامتر های ورودی و خروجی وب سرویس به صفحه معرفی
[وب سرویس ملی پیامک](https://github.com/Melipayamak/Webservices)
مراجعه نمایید .


<hr/>


### وب سرویس پیامک

برای استفاده از وب سرویس ملی پیامک، دستور زیر رار جهت اعطای دسترسی به اینترنت به پروژه اندروید خود اضافه کنید
</div>

```js
<uses-permission android:name="android.permission.INTERNET"/>
```

