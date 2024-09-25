<img src="ReadMeImages\Title.png" alt="Title" style="margin: auto;">
<p style="margin:auto;">
<img src="ReadMeImages\SpringBoot.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\Kotlin.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\TypeScript.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\react.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\mongodb.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\html.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\css.png" width="80px" height="80px" style="display: inline; margin-right: 5px;">
</p>

### [marketstack.quest](marketstack.quest) *NOT YET LIVE*
### Full Stack Trade Journaling Web Application


MarketStack is an application made to allow traders to get broadranging and unique statistics on their trading performance with the goal of improvement.

## Screenshots
*CURRENT IN-PROGRESS STATE"

### Home Page
<img src="ReadMeImages\HomePage.png" alt="Dashboard">

### Dashboard
<img src="ReadMeImages\Dashboard.png" alt="Dashboard">


---

## Goal Application

These are the core features the application should have in a fully developed version

### Import

Importing trades will be the core of this application and there are several complications to get through. Every broker send out a user's trade history in differently formated csv files so the application has to be capable of parsing majority of popular broker's format into a single json format to be received by the endpoint

### Dashboard

The heart of this application is the dashboard. Users should be able to enter their dashboard and be able to easily see the data they want to see to get an accurate view of what their performance has been in whatever period of time. This will be the landing page for a logged in account and this page will focus on various forms of permorance metrics. Things like equity curves, performance graphs and statistics should be easily accessable.

### Trade Filtering

One of the main ways a trader improves is by looking at their trades in abstract ways. This means being able to easily filter the performance of trades based on whatever the user desires. This functionality should be present in a variety of tools so that a user can use custom filters to see all kinds of metrics for those specific trades. whether the filter is based upon custom tags or other contraints it should be flexible.

### Secure Account and Login Systems

This kind of trade data is very sensitive and the architecture must take that into account. There must my a comprehensive registraion, login, and account services system that ensures the privacy and safety of sensative trade information in the database. Users should be able to access all their own data and nobody elses, as well as having the power to wipe any data at any time.

### Additional Tools and Features
Being a trader for over 5 years I have a deep understanding of the things that can be useful for traders and plan to include various quality of life and other essential tools for optimal trading performance. Things like equity curve generators and position calculators will be available on a section of the site and has lots of potential for expandability into larger features.



## App Architecture
 <img src="ReadMeImages\MarketStackArchitecture.png">

 ## Architecture Details

 ### Overview
The core function app is based upon the flow of data initialized by the user, flowed and sorted into the database structure and then retrieve and manipulated for the UI. For best understading of the structure you can observe the architecture diagram above and follow the route of data as described.

### Data Flow
Brokers will offer users their trade history in the form of csv files with data consisting of all trade executions taken in a certain period of time. This data can be inputted on the front end into a react component that will hit the end point /openTrades (Post End Point) which will parse the csv into valid JSON format and post the data. This end point is configured to receive objects called "TradeExec" which are meant to represent a single trade execution.

The endpoint then invokes the openTradeService class method "addTradeExecs". This method uses another component called data source to look inside the "Trade-Open" collection in the database. Once this information is retrieved the method will begin sorting the new trade executions into "OpenTrade" objects which are objects that represent trades that are still open positions. The service class will go 1 by 1 through the new executions and if an execution matches an existing "OpenTrade" object in the database, it will be added to that object, if not, a new "OpenTrade" object will be created with the corresponding execution being it's first and only execution. 

After each execution is placed in the right "OpenTrade" object, the service class runs a method to check if and of the "OpenTrade" objects have a position of 0 meaning that the trade was closed. Suppose there is an "OpenTrade" object in the collection that is an Apple long position currently at 50 shares. If the service class get's a new execution that is a sell for Apple for 50 shares, this trade is no longer open. So the service class will detect this and transfer the relant data to a new "ClosedTrade" object, delete the "OpenTrade" object from the "Trade-Open" collection and then add the new "ClosedTrade" object to the "Trade-Closed" collection. With all these services, once a user imports their trades, they should all get sorted to the right place in the data base.

The flow from the data base to the UI is a lot simpler since all the sorting was done on the way down. Any components on the user's UI will be hitting an endpoint that retreives a list of Trade objects, whether opened or closed, and displays them in different ways depending on the user's preferences.

### Class Diagram
 
 <img src="ReadMeImages\ClassDiagram.png" style="margin: auto;">

### Design

Majority of market based websites tend to go in the direction of neon colors because of the primary colors being red and green. I decided to take a different road and chose the following color pallette for a a more neutral dark theme approach. This is the garden pallette and focuses on more natural colors. All icons, logos, and images are customly made with adobe illustrator. All components have been customly styled in css with no external libraries.

<img src="ReadMeImages\Pallette.png" style="margin: auto;">
