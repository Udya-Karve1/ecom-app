package com.sk.rk.model;

public interface PropertyResponse {
    public Long getId ();
    public void setId (Long id);

    public String getKey ();
    public void setKey (String key);

    public String getValue ();
    public void setValue (String value);

    public Long getApplicationId ();
    public void setApplicationId (Long applicationId);

    public String getApplication();
    public void setApplication(String application);

    public Long getProfileId();
    public void setProfileId(Long profileId);

    public String getProfile();
    public void setProfile(String profileId);

    public String getLabel ();
    public void setLabel (String label);
}
