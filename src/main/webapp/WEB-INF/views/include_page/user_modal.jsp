
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="user_insert_modal"  role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="" style="min-height: 1px;">
                    <div class="row">
                        <div class="col-md-12">
                            <form class="user_insert_form" id="user_insert_form" role="form">
                                <table class="table borderless table-striped table-hover">
                                    <colgroup>
                                        <col width="25%">
                                        <col width="75%">
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <td>
                                            아이디
                                        </td>
                                        <td>
                                            <input type="text" class="form-control "  name="id" placeholder="아이디를 입력해주세요.">
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">

                <div class="col-xs-3"></div>
                <div class="col-xs-6" style="text-align: center;">
                    <button type="button" class="btn btn-primary" style="margin-right: 40px;" onclick="user_insert();">저장</button>
                    <button type="button" class="btn btn-primary" style="margin-right: 40px;" data-dismiss="modal">취소</button>
                </div>
                <div class="col-xs-3"></div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="user_update_modal" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="" style="    min-height: 1px;">
                    <div class="row">
                        <div class="col-md-12">
                            <form class="user_update_form" id="user_update_form" role="form">
                                <table class="table borderless table-striped table-hover">
                                    <colgroup>
                                        <col width="25%">
                                        <col width="75%">
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <td>
                                            아이디
                                        </td>
                                        <td>
                                            <input type="text" class="form-control "  name="id" placeholder="아이디를 입력해주세요.">
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">

                <div class="col-xs-3"></div>
                <div class="col-xs-6" style="text-align: center;">
                    <button type="button" class="btn btn-primary" style="margin-right: 40px;" onclick="user_update();">수정</button>
                    <button type="button" class="btn btn-primary" style="margin-right: 40px;" data-dismiss="modal">취소</button>
                </div>
                <div class="col-xs-3"></div>
            </div>
        </div>
    </div>
</div>