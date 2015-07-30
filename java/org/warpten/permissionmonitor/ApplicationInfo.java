package org.warpten.permissionmonitor;

import java.util.ArrayList;
import java.util.List;

public class ApplicationInfo {
    public String Name;
    public List<PkgInfo> Packages = new ArrayList<>();
    public int UID;
    public int PID;

    public String[] GetPermissions()
    {
        List<String> perms = new ArrayList<>();

        for (PkgInfo info : Packages)
            if (info.Permissions != null)
                for (String p : info.Permissions)
                    if (!perms.contains(p))
                        perms.add(p);

        return perms.toArray(new String[0]);
    }

    public String[] GetRequestedPermissions()
    {
        List<String> perms = new ArrayList<>();

        for (PkgInfo info : Packages)
            if (info.RequestedPermissions != null)
                for (String p : info.RequestedPermissions)
                    if (!perms.contains(p))
                        perms.add(p);

        return perms.toArray(new String[0]);
    }
}
