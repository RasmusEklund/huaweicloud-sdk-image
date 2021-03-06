# -*- coding:utf-8 -*-
from image_sdk.gettoken import get_token
from image_sdk.utils import encode_to_base64
from image_sdk.recapture_detect import recapture_detect
from image_sdk.utils import init_global_env

if __name__ == '__main__':
    # Services currently support North China-Beijing(cn-north-4), Asia Pacific-Hong Kong(ap-southeast-1)
    init_global_env('cn-north-4')

    #
    # access image recapture detect ,post data by token
    #
    user_name = '******'
    password = '******'
    account_name = '******'  # the same as user_name in commonly use

    # The OBS link should match the region, and the OBS resources of different regions are not shared
    demo_data_url = 'https://sdk-obs-source-save.obs.cn-north-4.myhuaweicloud.com/recapture-detect.jpg'
    token = get_token(user_name, password, account_name)

    # call interface use the file
    result = recapture_detect(token, encode_to_base64('data/recapture-detect-demo.jpg'), '', 0.75, ["recapture"])
    print(result)

    # call interface use the url
    result = recapture_detect(token, '', demo_data_url, 0.75, ["recapture"])
    print(result)
