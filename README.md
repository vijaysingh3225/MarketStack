
# MarketStack
### [marketstack.quest](marketstack.quest)
### Full Stack Trade Journaling Web Application


MarketStack is an application made to allow traders to get broadranging and unique statistics on their trading performance with the goal of improvement.

---

## Goal Application

These are the core features the application should have in a fully developed version

### Import

Importing trades will be the core of this application and there are several complications to get through. Every broker send out a user's trade history in differently formated csv files so the application has to be capable of parsing majority of popular broker's format into a single json format to be received by the endpoint

### Dashboard
<img src="ReadMeImages\Dashboard.png" alt="Dashboard">
*CURRENT IN-PROGRESS STATE"

The heart of this application is the dashboard. Users should be able to enter their dashboard and be able to easily see the data they want to see to get an accurate view of what their performance has been in whatever period of time. This will be the landing page for a logged in account and this page will focus on various forms of permorance metrics. Things like equity curves, performance graphs and statistics should be easily accessable

### Trade Filtering

One of the main ways a trader improves is by looking at their trades in abstract ways. This means being able to easily filter the performance of trades based on whatever the user desires. This functionality should be present in a variety of tools so that a user can use custom filters to see all kinds of metrics for those specific trades. whether the filter is based upon custom tags or other contraints it should be flexible.

# Secure Account and Login Systems

This kind of trade data is very sensitive and the architecture must take that into account. There must my a comprehensive registraion, login, and account services system that ensures the privacy and safety of sensative trade information in the database. Users should be able to access all their own data and nobody elses, as well as having the power to wipe any data at any time.

## Technology
<p>
<img src="ReadMeImages\SpringBoot.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\Kotlin.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\TypeScript.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\react.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\mongodb.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\html.png" width="80px" height="80px" style="display: inline;">
<img src="ReadMeImages\css.png" width="80px" height="80px" style="display: inline; margin-right: 5px;">
</p>

## App Architecture
 <img src="ReadMeImages\MarketStackArchitecture.png">

 ## Architecture Details

 ### Overview
The core function app is based upon the flow of data initialized by the user, flowed and sorted into the database structure and then retrieve and manipulated up to the UI. For best understading of the structure you can observe the architecture diagram above and follow the route of data as described.

### Data Flow
Brokers will offer users their trade history in the form of csv files with data consisting of all trade executions taken in a certain period of time. This data can be inputted on the front end into a react component that will hit the end point /openTrades (Post End Point) which will parse the csv into valid JSON format and post the data. This end point is configured to receive objects called "TradeExec" which are meant to represent a single trade execution. 
 
 <img src="ReadMeImages\ClassDiagram.png">