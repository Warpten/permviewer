package org.warpten.permissionmonitor;

import android.content.pm.PackageInfo;

public class PkgInfo {
    public String Name;
    public String[] Permissions;
    public String[] RequestedPermissions;

    public PkgInfo(android.content.pm.PackageInfo packageInfo)
    {
        Name = packageInfo.packageName;
        RequestedPermissions = packageInfo.requestedPermissions;

        if (packageInfo.permissions == null)
            return;

        Permissions = new String[packageInfo.permissions.length];
        for (int i = 0; i < packageInfo.permissions.length; ++i)
            Permissions[i] = packageInfo.permissions[i].name;
    }
}
