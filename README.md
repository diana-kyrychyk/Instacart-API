# Instacart-API


After recently joining Instacart's beta testing developer group, you decide to experiment with their new API.
  You know that the API returns item-specific display-ready strings
        like 10.0% higher than in-store or 5.0% lower than in-store
  that inform users when the price of an item is different from the one in-store.
  But you want to extend this functionality by giving people a better sense of **how much more they will be paying
  for their entire shopping cart**.

  Your app lets a user decide the total amount x they are willing to pay via Instacart over in-store prices.
  This you call their **price sensitivity**.
  Your job is to determine:
  - whether a given customer will be willing to pay for the given items in their cart based on their stated price sensitivity x.

#### Example 1

  For prices = [110, 95, 70],
  notes = ["10.0% higher than in-store", "5.0% lower than in-store", "Same as in-store"]
  and x = 5,
  the output should be solution(prices, notes, x) = true.

  In-store prices of the first and the second items are 100,
  and the price of the third item is 70, which means the customer is overpaying 10 - 5 + 0 = 5,
  which they are willing to do based on their price sensitivity.

#### Example 2


  For prices = [48, 165],
  notes = ["20.00% lower than in-store", "10.00% higher than in-store"]
  and x = 2,
  the output should be solution(prices, notes, x) = false.

  The in-store price of the first item is 60, and the second item is 150.
  The overpayment equals 15 - 12 = 3, which is too much for the customer to be willing to pay.