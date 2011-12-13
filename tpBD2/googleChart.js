// Load the Visualization API and the piechart package.
google.load('visualization', '1', {
    'packages':['corechart']
});
  
// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(drawCharts);
  
function drawCharts() {
    var jsonTerms = $.ajax({
        url: "getDataTerms.php",
        dataType:"json",
        async: false
    }).responseText;
	  
    // Create our data table out of JSON data loaded from server.
    var dataTerms = new google.visualization.DataTable(jsonTerms);

    // Instantiate and draw our chart, passing in some options.
    var outputTags = document.getElementById('div_tags');
    var tc1 = new TermCloud(outputTags);
    tc1.draw(dataTerms, null);
    //Nuvem de tags
    var jsonHash = $.ajax({
        url: "getDataHashtags.php",
        dataType:"json",
        async: false
    }).responseText;
	  
    // Create our data table out of JSON data loaded from server.
    var dataHashtags = new google.visualization.DataTable(jsonHash);

    // Instantiate and draw our chart, passing in some options.
    var outputHash = document.getElementById('div_hashtags');
    var tc2 = new TermCloud(outputHash);
    tc2.draw(dataHashtags, null);
}
google.load('visualization', '1', {
    'packages': ['geochart']
});
google.setOnLoadCallback(drawMarkersMap);

function drawMarkersMap() {
    var data = new google.visualization.DataTable();

    var jsonMaps = $.ajax({
        url: "getDataMaps.php",
        dataType:"json",
        async: false
    }).responseText;

    var options = {
        region: 'BR', 
        displayMode: 'markers', 
        colorAxis: {
            colors: ['green', 'red']
        }
    };
    
    var dataMaps = new google.visualization.DataTable(jsonMaps);

    var map = new google.visualization.GeoChart(document.getElementById('chart_div'));
    map.draw(dataMaps, options);
}
