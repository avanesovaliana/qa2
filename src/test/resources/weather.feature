Feature: Weather

  Scenario: Check correct Weather data
    Given show test name

    Scenario: Checking coordinates for city
      Given city "London"
      And country "UK"

      When we are requesting weather data

      Then lon is -0.13
      And lat is 51.51

      And temp is 280.32

      And speed of wind is 4.1

      And sunrise of sys is 1485762037

      And weather are:
      | id          |  300                      |
      | main        | "Drizzle"                 |
      | description | "light intensity drizzle" |
      | icon        | "09d"                     |


