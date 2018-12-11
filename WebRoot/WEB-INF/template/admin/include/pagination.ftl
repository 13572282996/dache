
<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
<!--<input type="hidden" id="searchProperty" name="searchProperty" value="${page.searchProperty}" />-->
<input type="hidden" id="orderProperty" name="orderProperty" value="${page.orderProperty}" />
<input type="hidden" id="orderDirection" name="orderDirection" value="${page.orderDirection}" />
[#if totalPages > 1]
	<div class="pagination" style="margin-top: 5px;margin-bottom: 5px;">
	<ul>
		[#if isFirst]
			<li ><a style="background-color: #f5f5f5;pointer-events: none;">首页&nbsp;</a></li>
		[#else]
			<li><a class="firstPage" href="javascript: $.pageSkip(${firstPageNumber});">首页&nbsp;</a></li>
		[/#if]
		[#if hasPrevious]
			<li><a class="previousPage" href="javascript: $.pageSkip(${previousPageNumber});">上一页&nbsp;</a></li>
		[#else]
			<li ><a style="background-color: #f5f5f5;pointer-events: none;">上一页&nbsp;</a></li>
		[/#if]
		[#list segment as segmentPageNumber]
			[#if segmentPageNumber_index == 0 && segmentPageNumber > firstPageNumber + 1]
				<li><span>...</span></li>
			[/#if]
			[#if segmentPageNumber != pageNumber]
				<li><a href="javascript: $.pageSkip(${segmentPageNumber});">${segmentPageNumber}</a></li>
			[#else]
				<li ><a style="background-color: #f5f5f5;pointer-events: none;">${segmentPageNumber}</a></li>
			[/#if]
			[#if !segmentPageNumber_has_next && segmentPageNumber < lastPageNumber - 1]
				<li><span>...</span></li>
			[/#if]
		[/#list]
		[#if hasNext]
			<li><a class="nextPage" href="javascript: $.pageSkip(${nextPageNumber});">下一页&nbsp;</a></li>
		[#else]
			<li ><a style="background-color: #f5f5f5;pointer-events: none;">下一页&nbsp;</a></li>
		[/#if]
		[#if isLast]
			<li ><a style="background-color: #f5f5f5;pointer-events: none;">尾页&nbsp;</a></li>
		[#else]
			<li><a class="lastPage" href="javascript: $.pageSkip(${lastPageNumber});">尾页&nbsp;</a></li>
		[/#if]
		<li>
		<span >
			${message("admin.page.totalPages", totalPages)} ${message("admin.page.pageNumber", '<input id="pageNumber" name="pageNumber" value="' + pageNumber + '" maxlength="9" onpaste="return false;" class="input-xlarge" style="width: 34px;"/>')}<button type="submit" class="btn">&gt;&gt;</button>
		</span>
		</li>
	</ul>	
	</div>
[/#if]