# Election Simulator

How would four candidates do with a given group of voters? Enter how many of them are under 35, Christian, university educated, living in a village, pensioners or entrepreneurs, and Election Simulator turns those numbers into a chance of winning for each candidate, shown as a pie chart. It is a JavaFX desktop app with user accounts and an admin screen.

## How a prediction is calculated

The four candidates are fixed, and each one has an orientation and a wing:

| Candidate | Orientation | Wing |
|---|---|---|
| Camila Hale | liberal | left |
| Paul Ford | liberal | right |
| Luis Pedro | conservative | left |
| Henry Blair | conservative | right |

Every candidate collects points for the orientation and points for the wing:

- Liberal candidates gain points for voters under 35, voters who are not Christian and voters who do not live in a village. Conservative candidates gain points for the opposite.
- Left candidates gain points for pensioners, voters who are not entrepreneurs and voters without a degree. Right candidates gain points for the opposite.

A candidate's chance of winning is their share of the points of all four. Take 10,000 voters, of whom 4,000 are under 35, 6,000 Christian, 3,000 graduates, 2,500 villagers, 2,000 pensioners and 500 entrepreneurs:

```
liberal 15,500    conservative 14,500    left 18,500    right 11,500

Camila Hale    15,500 + 18,500 = 34,000    28.3 %
Paul Ford      15,500 + 11,500 = 27,000    22.5 %
Luis Pedro     14,500 + 18,500 = 33,000    27.5 %
Henry Blair    14,500 + 11,500 = 26,000    21.7 %
```

This is a plain scoring rule, not a statistical model, and the link between traits and candidates is only an illustration, not a claim about real voters.

## Run it

You need JDK 21. The Maven Wrapper is included, so there is nothing else to install. Start the app from the project folder, because it keeps its data files in `src/main/resources/files` and looks for them relative to where it was started.

On Windows (PowerShell):

```powershell
$env:JAVA_HOME = "C:\Path\To\jdk-21"
.\mvnw.cmd javafx:run
```

The first line is only needed when your default JDK is older than 21, and it affects only that terminal window. On Linux and macOS use `./mvnw javafx:run`.

## A first session

1. Click Start, then Login as user, and create an account with New User.
2. Log in, open Simulation, fill in the numbers and click Predict winner.
3. Candidates lists the four candidates with the chances from your last prediction.

Login as admin (username and password `admin`) shows how many users have made a prediction and the combined result of all of them.

Accounts, predictions and totals are saved as plain text files in `src/main/resources/files`, so they survive a restart.

## Code

`src/main/java` is split into `controller` (the logic and the switching between screens, entry point `ElectionSim.java`), `model` (candidates, voters, political attributes) and `view` (the JavaFX screens).
