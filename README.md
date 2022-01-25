
# subscription
A single API endpoint to allow users to create a subscription.

Sample request 1:

    {
        "amount": 10.00,
        "subscriptionType":"DAILY",
        "duration": {
            "startDate":"1/01/2022",
            "endDate":"01/02/2022"
        }
    }

Output 1:

    {
        "amount": 10.00,
        "subscriptionType": "DAILY",
        "invoiceDates": [
            "01/01/2022",
            "02/01/2022",
            "03/01/2022",
            "04/01/2022",
            "05/01/2022",
            "06/01/2022",
            "07/01/2022",
            "08/01/2022",
            "09/01/2022",
            "10/01/2022",
            "11/01/2022",
            "12/01/2022",
            "13/01/2022",
            "14/01/2022",
            "15/01/2022",
            "16/01/2022",
            "17/01/2022",
            "18/01/2022",
            "19/01/2022",
            "20/01/2022",
            "21/01/2022",
            "22/01/2022",
            "23/01/2022",
            "24/01/2022",
            "25/01/2022",
            "26/01/2022",
            "27/01/2022",
            "28/01/2022",
            "29/01/2022",
            "30/01/2022",
            "31/01/2022",
            "01/02/2022"
        ]
    }

Sample request 2:

    {
        "amount": "10.00",
        "subscriptionType":"WEEKLY",
        "dayOfWeek": "SUNDAY",
        "duration": {
            "startDate":"1/1/2022",
            "endDate":"01/02/2022"
        }
    }

Output 2:

    {
        "amount": 10.00,
        "subscriptionType": "WEEKLY",
        "invoiceDates": [
            "02/01/2022",
            "09/01/2022",
            "16/01/2022",
            "23/01/2022",
            "30/01/2022"
        ]
    }

Sample request 3:

    {
        "amount": 10.00,
        "subscriptionType":"MONTHLY",
        "dayOfMonth": 31,
        "duration": {
            "startDate":"28/2/2022",
            "endDate":"31/03/2022"
        }
    }

Output 3:

    {
        "amount": 10.00,
        "subscriptionType": "MONTHLY",
        "invoiceDates": [
            "28/02/2022",
            "31/03/2022"
        ]
    }

For MONTHLY subscription, the subsription date will be adjusted to last day of the month if 'dayOfMonth' is larger than the last day of the month.
