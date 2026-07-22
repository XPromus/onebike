<script lang="ts">
	import TableCell from '$lib/components/table/TableCell.svelte';
	import TableHeader from '$lib/components/table/TableHeader.svelte';
	import TableRow from '$lib/components/table/TableRow.svelte';
	import type { PageData } from './$types';

    let { 
        data 
    }: {
        data: PageData
    } = $props();
</script>

<div class="space-y-2 flex overflow-hidden relative flex-col p-2 w-full h-full">
    <div class="flex flex-1 flex-col min-h-0 space-y-2">
        <div class="flex flex-row shrink-0">
            <TableHeader content="Name" />
            <TableHeader content="Short Name" />
            <TableHeader content="Nation" />
        </div>
        <div class="flex overflow-y-scroll flex-col flex-1 min-h-0 space-y-1">
            {#each data.teams as team }
                <TableRow>
                    <TableCell content={`${team.teamName}`} />
                    <TableCell content={`${team.shortName}`} />
                    <TableCell content={`${team.nation.flagEmoji} (${team.nation.shortName})`} />
                </TableRow>
            {/each}
        </div>
    </div>
</div>
