function loaded() {
    document.addEventListener("deviceready", onDeviceReady, false);
}

function onDeviceReady() {
    // Initializating TabBar
    alert('hai');
    nativeControls = window.plugins.nativeControls;
    nativeControls.createTabBar();
    
    // Books tab
    nativeControls.createTabBarItem(
    "books",
    "Books",
    "/www/images/account.png",
    {"onSelect": function() {
        books();
    }}
    );
    
    // Stats tab
    nativeControls.createTabBarItem(
    "finished",
    "Finished",
    "/www/images/explode.png",
    {"onSelect": function() {
        finished();
    }}
    );
    
    // About tab
    nativeControls.createTabBarItem(
    "about",
    "About",
    "/www/images/x.png",
    {"onSelect": function() {
        about();
    }}
    );
    
    // Compile the TabBar
    nativeControls.showTabBar();
    nativeControls.showTabBarItems("books", "finished", "about");
    nativeControls.selectTabBarItem("books");
}