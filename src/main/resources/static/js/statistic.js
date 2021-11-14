
var data = [];
var colors = [];
var sumAmountByCategoryData = [];
var transactionCategories = [];
var filterData;
var bankAccount;
var year;
var month;
var bank;
var myChart;

async function getAmountByCategory() {

    const response = await fetch('http://localhost:8500/getAmountByCategory');
    data = await response.json();
    
    getTransactionCategories();
    sumAmountByCategory(data);
    getChartData();
    eventListener();
}

function getTransactionCategories() {
    for (var i = 0; i < data.length; i += 1) {
        if (!transactionCategories.includes(data[i].transactionCategory.transactionCategoryName)) {
            transactionCategories.push(data[i].transactionCategory.transactionCategoryName);
            colors.push(getRandomColorHex());
        }
    }
}

function sumAmountByCategory(filterData) {
    sumAmountByCategoryData = [];

    for ( var i = 0; i < transactionCategories.length; i += 1 ) {
        var amounts = {
            category: "",
            amount: 0
        };
        amounts.category = transactionCategories[i];

        for (var j = 0; j < filterData.length; j += 1 ) {
            if (transactionCategories[i] == filterData[j].transactionCategory.transactionCategoryName) {
                if (filterData[j].amount < 0) {
                    amounts.amount +=  (filterData[j].amount * -1);
                }
                
            }
        }
        sumAmountByCategoryData.push(amounts);
    }
}

function getChartData() {
    pieConfigData(sumAmountByCategoryData.map(dataOption => dataOption.category), sumAmountByCategoryData.map(dataOption => dataOption.amount));
}

function pieConfigData(label, data) {
    var config = {
        type: 'doughnut',
        data: {
            labels: label,
            datasets: [{
                label: 'Kategóriák',
                data: data,
                borderWidth: 1,
                backgroundColor: colors
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    }
    renderChart(config);
}


getAmountByCategory();


function renderChart(config) {

    if(myChart) {
        myChart.destroy();
    }

    var ctx = document.getElementById('myChart');
    myChart = new Chart(ctx, config);

}

function eventListener() {
    var bankAccountFilter = document.querySelector('#bankAccount');
    bankAccountFilter.addEventListener('change', function filter() {
        updateChart();
    });
    var yearFilter = document.querySelector('#year');
    yearFilter.addEventListener('change', function filter() {
        updateChart();
    });
    var monthFilter = document.querySelector('#month');
    monthFilter.addEventListener('change', function filter() {
        updateChart();
    });
    var bankFilter = document.querySelector('#bank');
    bankFilter.addEventListener('change', function filter() {
        updateChart();
    });
}


function updateChart() {

    bankAccount = document.getElementById('bankAccount').value;
    year = document.getElementById('year').value;
    month = document.getElementById('month').value;
    bank = document.getElementById('bank').value;
    
    filterData = JSON.parse(JSON.stringify(data));    

    bankAccountFilter();
    yearFilter();
    monthFilter();
    bankFilter();
    sumAmountByCategory(filterData);
    getChartData();

}

function bankAccountFilter() {

    if (bankAccount != '--') {
        for (var i = 0; i < filterData.length; i += 1) {
            if (filterData[i].bankAccount.bankAccountId != bankAccount) {
                filterData.splice(i, 1);
                i--;
            }
        }
    }
}

function yearFilter() {
    if (year != '--') {
        for (var i = 0; i < filterData.length; i += 1) {
            var date = new Date(filterData[i].transactionDate).getFullYear();
            if (date != year) {
                console.log(date);
                filterData.splice(i, 1);
                i--;
            }
        }
    }
}

function monthFilter() {
    if (month != '--') {
        for (var i = 0; i < filterData.length; i += 1) {
            var date = new Date(filterData[i].transactionDate).getMonth();
            if (date != month) {
                filterData.splice(i, 1);
                i--;
            }
        }
    }
}

function bankFilter() {
    if (bank != '--') {
        for (var i = 0; i < filterData.length; i += 1) {
            if (filterData[i].bankAccount.bank.bankId != bank) {
                filterData.splice(i, 1);
                i--;
            }
        }
    }
}

function getRandomColorHex() {
    var hex = "0123456789ABCDEF",
        color = "#";
    for (var i = 1; i <= 6; i++) {
      color += hex[Math.floor(Math.random() * 16)];
    }
    return color;
  }