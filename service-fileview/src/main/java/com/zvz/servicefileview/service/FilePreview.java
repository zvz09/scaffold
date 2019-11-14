package com.zvz.servicefileview.service;

import com.zvz.servicefileview.model.FileAttribute;
import com.zvz.servicefileview.model.FileConverResp;


public interface FilePreview {
    FileConverResp filePreviewHandle(FileAttribute fileAttribute);
}
