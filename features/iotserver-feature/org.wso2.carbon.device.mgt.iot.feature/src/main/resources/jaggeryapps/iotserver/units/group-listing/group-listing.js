var log = new Log("modules/group-listing.js");

function onRequest(context){

    var dcProps = require('/config/dc-props.js').config();
    context.appContext = dcProps.appContext;

    var constants = require("/modules/constants.js");
    var permissions = [];
    //var userModule = require("/modules/user.js").userModule;
    //if(userModule.isAuthorized("/permission/device-mgt/admin/groups/list")){
    //    permissions.push("LIST_GROUPS");
    //}else if(userModule.isAuthorized("/permission/device-mgt/groups/remove")){
    //    permissions.push("REMOVE_GROUPS");
    //}else if(userModule.isAuthorized("/permission/device-mgt/groups/share")){
    //    permissions.push("SHARE_GROUPS");
    //}
    permissions.push("LIST_GROUPS");
    permissions.push("ADD_GROUPS");
    permissions.push("SHARE_GROUPS");
    context.permissions = stringify(permissions);
    context.SHARE_GROUPS = true;
    context.user = session.get(constants.USER_SESSION_KEY);
    return context;
}