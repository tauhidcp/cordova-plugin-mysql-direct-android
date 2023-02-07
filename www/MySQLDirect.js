var exec = require('cordova/exec');

exports.getQuery = function (arg0, success, error) {
    exec(success, error, 'MySQLDirect', 'getQuery', [arg0]);
};

exports.execQuery = function (arg0, success, error) {
    exec(success, error, 'MySQLDirect', 'execQuery', [arg0]);
};

exports.Connect = function (arg0, arg1, arg2, arg3, arg4, success, error) {
    exec(success, error, 'MySQLDirect', 'Connect', [arg0, arg1, arg2, arg3, arg4]);
};
